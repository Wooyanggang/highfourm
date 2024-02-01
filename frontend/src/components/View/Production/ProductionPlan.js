import React, { useState } from 'react';
import Container from '../../Common/PageTitle';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import PageTitle from '../../Common/PageTitle';

const ProductionPlan = () => {

  const [productionPlan, setProductionPlan] = useState([]);
  const [monthlyProductionPlan, setMonthlyProductionPlan] = useState([]);

  const productionPlanColumns = [
    {
      title: '생산계획 코드',
      dataIndex: 'productPlanCode',
      width: '20%',
    },
    {
      title: '주문 번호',
      dataIndex: 'orderId',
    },
    {
      title: '품명',
      dataIndex: 'productName',
    },
    {
      title: '주문 수량',
      dataIndex: 'productAmount',
    },
    {
      title: '생산 계획 수량',
      dataIndex: 'productPlanAmount',
    },
    {
      title: '주문일',
      dataIndex: 'orderDate',
    },
    {
      title: '착수일',
      dataIndex: 'productionStartDate',
    },
    {
      title: '납기일',
      dataIndex: 'dueDate',
    },
  ];

  const monthlyProductionPlanColumns = [
    {
      title: '월',
      dataIndex: 'month',
      width: '50%',
    },
    {
      title: '생산계획 수량',
      dataIndex: 'procution_amount',
      editable: true,
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

  const [count, setCount] = useState(productionPlan.length);
  const handleAdd = () => {
    const newData = {
      key: count,
      product_plan_code: `PP24011101${count}`,
      product_name: '키보드',
      procution_unit: 'EA',
      amount: '500',
      oder_date: '2024-01-11',
      production_start_date: '2024-01-12',
      due_date: '2024-03-02',
    };
    setProductionPlan([...productionPlan, newData]);
    setCount(count + 1);
  };

  return (
    <div>
      <PageTitle value={'생산 계획 수립'} />
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['생산계획 코드', '품명', '주문일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'생산계획 등록'} onClick={handleAdd} />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div style={{ paddingRight: '20px', width: '50%' }}>
          <BasicTable dataSource={productionPlan} defaultColumns={productionPlanColumns} setDataSource={setProductionPlan} />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px' }}>
            <h2 className='bordered-box-title'>월별 생산 계획</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable dataSource={monthlyProductionPlan} defaultColumns={monthlyProductionPlanColumns} setDataSource={setMonthlyProductionPlan} />
        </div>
      </div>
    </div>
  )
}

export default ProductionPlan;
