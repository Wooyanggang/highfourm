import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { SearchInput, SearchSelectBox, StepBar } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';

function Status() {
  const stepUrl = ['/orders','product-management','work-performance','production-performance']
  const [dataProductionPlan, setDataProductionPlan] = useState([]);
  const [dataWorkPerformance, setDataWorkPerformance] = useState([]);
  const [dataSource, setDataSource] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('/production-status');
        const result = await response.json();
        console.log(result);

        let steps;
        if (result["productionPlan"]) {
          const newDataProductionPlan = result["productionPlan"].map((item, index) => ({
            key: index,
            productionPlanId: item.productionPlanId,
            productName: item.productName,
            productionPlanAmount: item.productionPlanAmount,
            orderId: item.orderId,
            productionStartDate: item.productionStartDate,
          }));
          setDataProductionPlan(newDataProductionPlan);

          const newDataWithSteps = newDataProductionPlan.map((item) => {
            if(item.productionStartDate==null){
              return{
                ...item,
                step: 0,
              }
            }
            const totalProduction = result["workPerfomance"]
            .filter((work) => work.productionPlanId === item.productionPlanId)
            steps = (totalProduction.length== 0)? 1:2

            const totalProductionAmount = totalProduction
              .reduce((total, work) => total + work.productionAmount, 0);
            steps = totalProductionAmount >= item.productionPlanAmount ? 3 : 2;

            return {
              ...item,
              step: steps,
            };
          });

          setDataSource(newDataWithSteps);
        }
      } catch (error) {
        console.error('데이터를 가져오는 중 오류 발생:', error);
      }
    };

    fetchData();
  }, []);

  console.log(dataSource);

  const defaultColumns = [
    {
      title: '생산 계획 코드',
      dataIndex: 'productionPlanId',
    },
    {
      title: '주문 코드',
      dataIndex: 'orderId',
    },
    {
      title: '제품명',
      dataIndex: 'productName',
    },
    {
      title: '진행 단계',
      dataIndex: 'step',
      render: (state) => 
      <a href={`/${stepUrl[state]}`}>
        <StepBar stateNum={state}></StepBar>
      </a>
    },
  ];

  const SelectChangeHandler = (value) => {
    console.log(`selected ${value}`);
    // select 값 선택시 기능 구현 핸들러
    // 각 페이지에서 구현해주세요
  };

  const onSearch = (value, _e, info) => {
    // search 값 기능 구현 함수
    console.log(info?.source, value);
  }

  return (
    <div className='status-page'>
      <PageTitle value={'생산 현황 조회'}/>
      {/* <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', marginBottom: '24px', alignItems: 'center' }}>
        <SearchSelectBox selectValue={['주문 코드', '제품명']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div> */}
      <BasicTable
      dataSource={dataSource} 
      defaultColumns={defaultColumns} 
      setDataSource={setDataSource}
      pagination={false} 
      />
    </div>
  );
}

export default Status;
