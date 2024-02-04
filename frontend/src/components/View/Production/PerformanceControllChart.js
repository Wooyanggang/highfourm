import React, { useState, PureComponent, useEffect } from 'react';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Progress, Popconfirm, message } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList, Label, Area, ComposedChart, Bar, ReferenceLine } from 'recharts';
import PageTitle from '../../Common/PageTitle';
import axios from 'axios';
import { useParams } from 'react-router';

/* 관리도는 임시. 관련한 건 재작성할 예정 */

const handleMenuClick = (e) => {
  message.info('Click on menu item.');
  console.log('click', e);
};

const calculateControlLimits = (data, zValue) => {
  //p형 관리도의 p값. 통계검정에서 사용하는 p값 아님.
  const pValues = data.map(item => item.defectiveAmount / item.productionAmount);
  const barP = pValues.reduce((acc, p) => acc + p, 0) / pValues.length;
  const standardDeviation = Math.sqrt(barP * (1 - barP) / data.length);

  const UCL = (barP + zValue * standardDeviation) * 100;
  const LCL = (barP - zValue * standardDeviation) * 100;

  return {
    controlLimits: { UCL: UCL < 0 ? 0 : UCL, LCL: LCL < 0 ? 0 : LCL },
    processedData: data.map(item => ({
      ...item,
      pValue: (item.defectiveAmount / item.productionAmount) * 100,
    })),
  };
};

const PerformanceControllChart = () => {
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

  const defaultColumns = [
    {
      title: '생산 일자',
      dataIndex: 'date',
      width: '30%',
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

  const zValue = 1.96;
  const { controlLimits, processedData } = calculateControlLimits(data, zValue);
  const averageDefectRate = processedData.reduce((sum, item) => sum + item.pValue, 0) / processedData.length;

  const product = [
    {
    },
  ]

  return (
    <>
      <PageTitle value={'생산 실적 상세 조회'} />
      <div style={{ width: '100%' }}>
        <div className='order-filter'>
          <BtnFilter valueArr={['통계', '불량률 관리']} linkArr={[`/production-performance/${productionPlanId}/chart`, `/production-performance/${productionPlanId}/controll-chart`]} />
        </div>
        <div className='perfomance-chart-page' style={{ marginTop: '20px' }} >
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
        </div>
        <h3 style={{ marginTop: '16px' }}>공정 부적합률 P-관리도 <hr></hr></h3>
        <div>
          <div className='chart-wrap' style={{ display: 'flex', flexDirection: 'row', minHeight: '500px', width: '100%' }}>
            <div style={{ marginTop: '16px', width: '50%' }} >
              <ResponsiveContainer width="90%" height={500} >
                <ComposedChart data={processedData}>
                  <CartesianGrid stroke="#f5f5f5" />
                  <XAxis dataKey="date" />
                  <YAxis domain={['dataMin > 0 ? (dataMin * 0.8) : 0', 'dataMax < 1 ? (dataMax * 1.2) : 1']} />
                  <Tooltip />
                  <Legend />
                  <Line type="monotone" dataKey="pValue" stroke="#413ea0" name="불량률" />
                  <ReferenceLine y={controlLimits.UCL} stroke="red" ifOverflow="extendDomain">
                    <Label value={`UCL=${controlLimits.UCL.toFixed(2)}%`} position="insideTopRight" offset={10} />
                  </ReferenceLine>
                  <ReferenceLine y={averageDefectRate.toFixed(2)} stroke="blue" label={{ value: `barP`, position: 'left' }} />
                  <ReferenceLine y={controlLimits.LCL} stroke="green" ifOverflow="extendDomain">
                    <Label value={`LCL=${controlLimits.LCL.toFixed(2)}%`} position="insideBottomRight" offset={10} />
                  </ReferenceLine>
                </ComposedChart>
              </ResponsiveContainer>
            </div>
            <div className='controll-chart' style={{ marginTop: '16px', width: '40%' }}>
              <BasicTable dataSource={data} defaultColumns={defaultColumns} />
            </div>
          </div>
        </div>
      </div >
    </>
  );
};

export default PerformanceControllChart;


