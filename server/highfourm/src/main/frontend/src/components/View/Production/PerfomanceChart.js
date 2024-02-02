import React, { useState, PureComponent } from 'react';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Progress, Popconfirm, message } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList, Area, ComposedChart, Bar } from 'recharts';
import PageTitle from '../../Common/PageTitle';


const handleMenuClick = (e) => {
  message.info('Click on menu item.');
  console.log('click', e);
};

const PerfomanceChart = () => {

  const defaultColumns = [
    {
      title: '생산 일자',
      dataIndex: 'date',
      width: '30%',
      editable: true,
    },
    {
      title: '작업 시간',
      dataIndex: 'workTime',
    },
    {
      title: '생산 수량',
      dataIndex: 'productionAmount',
    },
    {
      title: '불량 수량',
      dataIndex: 'defective',
    },
    {
      title: '불량률',
      dataIndex: 'defectRate',
      render: (_, record) => `${Math.round((record.defective / record.productionAmount) * 100 * 100) / 100}%`,
    },
  ];

  const data = [
    {
      date: '23-12-01',
      productionAmount: 1000,
      defective: 8,
      workTime: 26,
    },
    {
      date: '23-12-02',
      productionAmount: 2400,
      defective: 13,
      workTime: 26,
    },
    {
      date: '23-12-03',
      productionAmount: 2000,
      defective: 9,
      workTime: 26,
    },
    {
      date: '23-12-04',
      productionAmount: 2780,
      defective: 14,
      workTime: 26,
    },
    {
      date: '23-12-05',
      productionAmount: 1890,
      defective: 15,
      workTime: 26,
    },
    {
      date: '23-12-06',
      productionAmount: 2390,
      defective: 7,
      workTime: 26,
    },
    {
      date: '23-12-07',
      productionAmount: 3490,
      defective: 20,
      workTime: 26,
    },
    {
      date: '23-12-08',
      productionAmount: 1490,
      defective: 7,
      workTime: 26,
    },
    {
      date: '23-12-09',
      productionAmount: 2310,
      defective: 7,
      workTime: 26,
    },
    {
      date: '23-12-10',
      productionAmount: 1500,
      defective: 6,
      workTime: 26,
    },
    {
      date: '23-12-11',
      productionAmount: 3490,
      defective: 14,
      workTime: 26,
    },
    {
      date: '23-12-12',
      productionAmount: 2460,
      defective: 7,
      workTime: 26,
    },
    {
      date: '23-12-13',
      productionAmount: 1990,
      defective: 17,
      workTime: 26,
    },
    {
      date: '23-12-14',
      productionAmount: 1700,
      defective: 5,
      workTime: 26,
    },
    {
      date: '23-12-15',
      productionAmount: 1990,
      defective: 9,
      workTime: 26,
    },
    {
      date: '23-12-16',
      productionAmount: 1490,
      defective: 15,
      workTime: 26,
    },
  ];

  const product = [
    {
    },
  ]

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
      <PageTitle value={'생산 실적 상세 조회'}/>
      <div style={{ width: '100%' }}>
        <div className='order-filter'>
          <BtnFilter valueArr={['통계', '불량률 관리']} linkArr={['/production-perfomance/chart', '/production-perfomance/controll-chart']} />
        </div>
        <div className='perfomance-chart-page' style={{ marginTop: '20px' }}>
          <form action='' className='searchForm'>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label htmlFor="order-number">주문 번호</label>
                <InputBar disabled={'disabled'} id={'orderNumber'} value={'주문번호'} />
              </div>
              <div className='search-input'>
                <label htmlFor="amount">주문 수량</label>
                <InputBar disabled={'disabled'} id={'amount'} value={'주문 수량'} />
              </div>
            </div>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label htmlFor="orderDate">주문일</label>
                <InputBar disabled={'disabled'} inputId={'orderDate'} value={'주문일'} />
              </div>
              <div className='search-input'>
                <label htmlFor="productionAmount">생산 수량</label>
                <InputBar disabled={'disabled'} inputId={'productionAmount'} value={'생산 수량'} />
              </div>
            </div>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label htmlFor="dueDate">납기일</label>
                <InputBar disabled={'disabled'} inputId={'dueDate'} value={'납기일'} />
              </div>
              <div className='search-input'>
                <label htmlFor="presentState">진행 상태</label>
                <InputBar disabled={'disabled'} inputId={'presentState'} value={'진행 상태'} />
              </div>
            </div>
          </form>
          <div className='process' style={{ width: '80%', display: 'flex', flexDirection: 'column', marginTop: '16px' }}>
            <div>
              <span>납기</span>
              <Progress percent={70} size="small" />
            </div>
            <div>
              <span>생산 진척도</span>
              <Progress percent={50} size="small" status="active" />
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
                <Bar yAxisId="right" name="불량품" type="monotone" dataKey="defective" stroke="#82ca9d" barSize={20} fill="#413ea0" label={<CustomizedLabelBottom />} />
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

export default PerfomanceChart;


