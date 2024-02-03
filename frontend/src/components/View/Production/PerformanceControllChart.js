import React, { useState, PureComponent } from 'react';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Progress, Popconfirm, message } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList, Label, Area, ComposedChart, Bar, ReferenceLine } from 'recharts';
import PageTitle from '../../Common/PageTitle';


/* 관리도는 임시. 관련한 건 재작성할 예정 */

const handleMenuClick = (e) => {
  message.info('Click on menu item.');
  console.log('click', e);
};

const calculateControlLimits = (data, zValue) => {
  const pValues = data.map(item => item.defective / item.productionAmount);
  const barP = pValues.reduce((acc, p) => acc + p, 0) / pValues.length;
  const standardDeviation = Math.sqrt(barP * (1 - barP) / data.length);

  const UCL = (barP + zValue * standardDeviation) * 100;
  const LCL = (barP - zValue * standardDeviation) * 100;

  return {
    controlLimits: { UCL: UCL < 0 ? 0 : UCL, LCL: LCL < 0 ? 0 : LCL },
    processedData: data.map(item => ({
      ...item,
      pValue: (item.defective / item.productionAmount) * 100,
    })),
  };
};

const PerformanceControllChart = () => {
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

  const defaultColumns = [
    {
      title: '생산 일자',
      dataIndex: 'date',
      width: '30%',
      editable: true,
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
          <BtnFilter valueArr={['통계', '불량률 관리']} linkArr={['/production-perfomance/chart', '/production-perfomance/controll-chart']} />
        </div>
        <div className='perfomance-chart-page' style={{ marginTop: '20px' }} >
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
                  <ReferenceLine y={averageDefectRate} stroke="blue" ifOverflow="extendDomain">
                    <Label value={`평균 불량률=${averageDefectRate.toFixed(2)}%`} position="insideBottomRight" offset={10} />
                  </ReferenceLine>
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


