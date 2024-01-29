import React, { useState } from 'react';
import { SearchInput, SearchSelectBox } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import PageTitle from '../../Common/PageTitle';

const Mrp = () => {

  const [dataSourceOne, setDataSourceOne] = useState([
    {
      key: '0',
      due_date: '2024-01-11',
      production_plan_id: 'PP24011101',
      product_id: 'P1111111',
      product_name: '키보드',
      production_amount: '500',
    },
    {
      key: '1',
      due_date: '2024-01-15',
      production_plan_id: 'PP24011501',
      product_id: 'P1111112',
      product_name: '키보드',
      production_amount: '200',
    },
    {
      key: '2',
      due_date: '2024-01-15',
      production_plan_id: 'PP24011502',
      product_id: 'P1111113',
      product_name: '태블릿',
      production_amount: '300',
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
      // production_plan
    },
    {
      title: '생산계획 코드',
      dataIndex: 'production_plan_id',
      // production_plan
    },
    {
      title: '품번',
      dataIndex: 'product_id',
      // production_plan
    },
    {
      title: '품명',
      dataIndex: 'product_name',
      // production_plan(product_id) - product
    },
    {
      title: '계획 수량',
      dataIndex: 'production_amount',
      // production_plan
    },
  ];

  const defaultColumnsTwo = [
    {
      title: '자재명',
      dataIndex: 'material_name',
      width: '20%',
      // production_plan(product_id) - required_material(material_id) - material(material_name)
    },
    {
      title: '자재 코드',
      dataIndex: 'material_id',
      // production_plan(product_id) - required_material(material_id)
    },
    {
      title: '단위',
      dataIndex: 'input_unit',
      // production_plan(product_id) - required_material(input_unit)
    },
    {
      title: '총 소요 수량',
      dataIndex: 'total_material_amount',
      // production_plan(production_plan_amount) * required_material(input_unit)
    },
    {
      title: '현 재고',
      dataIndex: 'total_stock',
      // production_plan(product_id) - required_material(material_id) - material_stock(total_stock)
    },
    {
      title: '안전 재고',
      dataIndex: 'safety_stock',
      // production_plan(product_id) - required_material(material_id) - material_stock(safety_stock)
    },
    {
      title: '입고 예정량',
      dataIndex: 'inbound_amount',
      // production_plan(product_id) - required_material(material_id) - material_history(order_amount)
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
      <PageTitle value={'자재 소요량 산출'}/>
      <div style={{ display: 'flex', gap: '10px 24px', marginBottom: '24px', alignItems: 'center' }}>
        <h2 style={{ fontSize: '16px', margin: 0 }}>생산계획 조회 조건</h2>
        <SearchSelectBox selectValue={['생산계획 코드', '품번', '품명', '납기일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>생산계획 상세</h2>
            <hr className='box-title-line' />
          </div>
          <div style={{ height: '706px', overflowY: 'auto' }}>
            <BasicTable dataSource={dataSourceOne} defaultColumns={defaultColumnsOne} setDataSource={setDataSourceOne} />
          </div>
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>자재소요 계획</h2>
            <hr className='box-title-line' />
          </div>
          <div style={{ height: '706px', overflowY: 'auto' }}>
            <BasicTable dataSource={dataSourceTwo} defaultColumns={defaultColumnsTwo} setDataSource={setDataSourceTwo} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default Mrp

