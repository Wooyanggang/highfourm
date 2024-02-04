import React, { useState, PureComponent, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Progress, Popconfirm, message } from 'antd';
import axios from 'axios';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList, Area, ComposedChart, Bar } from 'recharts';
import PageTitle from '../../Common/PageTitle';

const PerformanceChart = () => {
  const { productionPlanId } = useParams();
  const [performance, setPerformance] = useState(null);
  const [data, setData] = useState([]);

  useEffect(() => {
    if (productionPlanId) {
      axios.get(`/production-performance/${productionPlanId}/chart`)
        .then(res => {
          setPerformance(res.data);

          const transformedData = res.data.workPerformances.map(wp => ({
            date: wp.date,
            productionAmount: wp.productionAmount,
            defectiveAmount: wp.defectiveAmount,
            workingTime: wp.workingTime,
          }));
          setData(transformedData);
        })
        .catch(error => {
          console.error('Error fetching performance data:', error);
          setPerformance(null);
          setData([]);
        });
    }
  }, [productionPlanId]);

  const calculateDeadlinePercentage = () => {
    if (!performance || !performance.orderDate || !performance.dueDate) return 0;
    const orderDate = new Date(performance.orderDate);
    const dueDate = new Date(performance.dueDate);
    const currentDate = new Date();
    const totalDays = (dueDate - orderDate) / (1000 * 3600 * 24);
    const elapsedDays = (currentDate - orderDate) / (1000 * 3600 * 24);
    return Math.min(Math.max((elapsedDays / totalDays) * 100, 0), 100);
  };

  const calculateProgressPercentage = () => {
    if (!performance || !performance.totalProductionAmount || !performance.productAmount) return 0;
    return Math.min(Math.ceil((performance.totalProductionAmount / performance.productAmount) * 100), 100);
  };

  const defaultColumns = [
    {
      title: '생산 일자',
      dataIndex: 'date',
      width: '30%',
    },
    {
      title: '작업 시간',
      dataIndex: 'workingTime',
    },
    {
      title: '생산 수량',
      dataIndex: 'productionAmount',
    },
    {
      title: '불량 수량',
      dataIndex: 'defectiveAmount',
    },
    {
      title: '불량률',
      dataIndex: 'defectRate',
      render: (_, record) => `${Math.round((record.defectiveAmount / record.productionAmount) * 100 * 100) / 100}%`,
    },
  ];

  class CustomizedLabelTop extends PureComponent {
    render() {
      const { x, y, stroke, value } = this.props;

      return (
        <text x={x + 15} y={y} dy={-6} fill={stroke} fontSize={10} textAnchor="middle">
          {value}
        </text>
      );
    }
  }

  class CustomizedLabelBottom extends PureComponent {
    render() {
      const { x, y, stroke, value } = this.props;

      return (
        <text x={x + 10} y={y} dy={-5} fill={stroke} fontSize={10} textAnchor="middle">
          {value}
        </text>
      );
    }
  }

  class CustomizedAxisTick extends PureComponent {
    render() {
      const { x, y, stroke, payload } = this.props;

      return (
        <g transform={`translate(${x},${y})`}>
          <text x={0} y={0} dy={16} textAnchor="end" fill="#666" transform="rotate(-35)">
            {payload.value}
          </text>
        </g>
      );
    }
  }

  return (
    <>
      <PageTitle value={'생산 실적 상세 조회'} />
      <div style={{ width: '100%' }}>
        <div className='order-filter'>
          <BtnFilter valueArr={['통계', '불량률 관리']} linkArr={[`/production-performance/${productionPlanId}/chart`, `/production-performance/${productionPlanId}/controll-chart`]} />
        </div>
        <div className='perfomance-chart-page' style={{ marginTop: '20px' }}>
          <form action='' className='searchForm'>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label htmlFor="order-number">주문 번호</label>
                <InputBar disabled={'disabled'} id={'orderId'} value={performance ? performance.orderId : ''} />
              </div>
              <div className='search-input'>
                <label htmlFor="amount">주문 수량</label>
                <InputBar disabled={'disabled'} id={'amount'} value={performance ? performance.productAmount : ''} />
              </div>
            </div>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label htmlFor="orderDate">주문일</label>
                <InputBar disabled={'disabled'} id={'orderDate'} value={performance ? performance.orderDate : ''} />
              </div>
              <div className='search-input'>
                <label htmlFor="totalProductionAmount">생산량</label>
                <InputBar disabled={'disabled'} id={'totalProductionAmount'} value={performance ? performance.totalProductionAmount : ''} />
              </div>
            </div>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label htmlFor="dueDate">납기일</label>
                <InputBar disabled={'disabled'} id={'dueDate'} value={performance ? performance.dueDate : ''} />
              </div>
              <div className='search-input'>
                <label htmlFor="presentState">진행 상태</label>
                <InputBar disabled={'disabled'} id={'presentState'} value={performance && performance.totalProductionAmount >= performance.productAmount ? '완료' : '진행 중'} />
              </div>
            </div>
          </form>
          <div className='process' style={{ width: '80%', display: 'flex', flexDirection: 'column', marginTop: '16px' }}>
            <div>
              <span>납기</span>
              <Progress percent={calculateDeadlinePercentage()} size="small" />
            </div>
            <div>
              <span>생산 진척도</span>
              <Progress percent={calculateProgressPercentage()} size="small" />
            </div>
          </div>
          <h3>생산 현황<hr></hr></h3>
          <div style={{ width: '100%', height: '300px' }}>
            <ResponsiveContainer width="100%" height="100%">
              <ComposedChart
                width={500}
                height={300}
                data={data}
                margin={{
                  top: 20,
                  right: 30,
                  left: 20,
                  bottom: 5,
                }}
              >
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" tick={<CustomizedAxisTick />} height={60} />
                <YAxis yAxisId="left" value='생산량' label={{ value: '생산량', angle: -90, position: 'insideLeft' }} />
                <YAxis yAxisId="right" orientation="right" label={{ value: '불량', angle: -90, position: 'outsideRight' }} />
                <Tooltip />
                <Legend />
                <Bar yAxisId="right" name="불량품" type="monotone" dataKey="defectiveAmount" stroke="#82ca9d" barSize={20} fill="#413ea0" label={<CustomizedLabelBottom />} />
                <Line yAxisId="left" name="생산량" type="monotone" dataKey="productionAmount" stroke="#8884d8" activeDot={{ r: 8 }} label={<CustomizedLabelTop />} />
              </ComposedChart>
            </ResponsiveContainer>
          </div>
        </div>
        <div style={{ marginTop: '16px' }}>
          <BasicTable dataSource={data} defaultColumns={defaultColumns} />
        </div>
      </div>
    </>
  );
};

export default PerformanceChart;


