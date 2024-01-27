import React, { useState } from 'react';
import { SearchInput, SearchSelectBox, BtnFilter } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';

const Mrp = () => {

  const [dataSourceOne, setDataSourceOne] = useState([
    {
      key: '0',
      due_date: '2024-01-11',
      product_plan_code: 'PP24011101',
      product_code: 'P1111111',
      product_name: '키보드',
      amount: '500',
    },
    {
      key: '1',
      due_date: '2024-01-15',
      product_plan_code: 'PP24011501',
      product_code: 'P1111112',
      product_name: '키보드',
      amount: '200',
    },
    {
      key: '2',
      due_date: '2024-01-15',
      product_plan_code: 'PP24011502',
      product_code: 'P1111113',
      product_name: '태블릿',
      amount: '300',
    },
  ]);
  const [dataSourceTwo, setDataSourceTwo] = useState([
    {
      key: '0',
      material_name: '키캡1',
      material_code: 'M32',
      input_unit: 'EA',
      material_amount: '50',
      total_inventory: '5000',
      safety_inventory: '2500',
      expected_stock: '1000',
    },
    {
      key: '1',
      material_name: '키캡2',
      material_code: 'M30',
      input_unit: 'EA',
      material_amount: '28',
      total_inventory: '5000',
      safety_inventory: '2500',
      expected_stock: '1000',
    },
  ]);


  const defaultColumnsOne = [
    {
      title: '납기일',
      dataIndex: 'due_date',
      width: '20%',
    },
    {
      title: '생산계획 코드',
      dataIndex: 'product_plan_code',
    },
    {
      title: '품번',
      dataIndex: 'product_code',
    },
    {
      title: '품명',
      dataIndex: 'product_name',
    },
    {
      title: '계획 수량',
      dataIndex: 'amount',
    },
  ];

  const defaultColumnsTwo = [
    {
      title: '자재명',
      dataIndex: 'material_name',
      width: '20%',
    },
    {
      title: '자재 코드',
      dataIndex: 'material_code',
    },
    {
      title: '단위',
      dataIndex: 'input_unit',
    },
    {
      title: '총 소요 수량',
      dataIndex: 'material_amount',
    },
    {
      title: '현 재고',
      dataIndex: 'total_inventory',
    },
    {
      title: '안전 재고',
      dataIndex: 'safety_inventory',
    },
    {
      title: '입고 예정량',
      dataIndex: 'expected_stock',
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
    <div>
      <div style={{ display: 'flex', gap: '10px 24px', marginBottom: '24px', alignItems: 'center' }}>
        <h2 style={{ fontSize: '16px', margin: 0 }}>생산계획 조회 조건</h2>
        <SearchSelectBox selectValue={['생산계획 코드', '품번', '품명', '자재명', '자재코드']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} onSearch={onSearch} />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>생산계획 상세</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable dataSource={dataSourceOne} defaultColumns={defaultColumnsOne} setDataSource={setDataSourceOne} />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>자재소요 계획</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable dataSource={dataSourceTwo} defaultColumns={defaultColumnsTwo} setDataSource={setDataSourceTwo} />
        </div>
      </div>
    </div>
  )
}

export default Mrp

