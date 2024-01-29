import React, { useState } from 'react';
import Container from '../../Common/PageTitle';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';

const ProductManagement = () => {

  const [dataSourceOne, setDataSourceOne] = useState([
    {
      key: '0',
      product_plan_code: 'PP24011101',
      product_name: '키보드',
      procution_unit: 'EA',
      amount: '500',
      oder_date: '2024-01-11',
      production_start_date: '2024-01-12',
      due_date: '2024-03-02',
    },
    {
      key: '1',
      product_plan_code: 'PP24011501',
      product_name: '키보드',
      procution_unit: 'EA',
      amount: '200',
      oder_date: '2024-01-15',
      production_start_date: '2024-01-15',
      due_date: '2024-03-02',
    },
  ]);
  const [dataSourceTwo, setDataSourceTwo] = useState([
    {
      key: '0',
      month: '1',
      procution_amount: '250',
    },
    {
      key: '1',
      month: '2',
      procution_amount: '250',
    },
  ]);

  const defaultColumnsOne = [
    {
      title: '생산계획 코드',
      dataIndex: 'product_plan_code',
      width: '20%',
    },
    {
      title: '품명',
      dataIndex: 'product_name',
    },
    {
      title: '생산 단위',
      dataIndex: 'procution_unit',
    },
    {
      title: '주문 수량',
      dataIndex: 'amount',
    },
    {
      title: '주문일',
      dataIndex: 'oder_date',
    },
    {
      title: '착수일',
      dataIndex: 'production_start_date',
    },
    {
      title: '납기일',
      dataIndex: 'due_date',
    },
  ];


  const defaultColumnsTwo = [
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

  const [count, setCount] = useState(dataSourceOne.length);
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
    setDataSourceOne([...dataSourceOne, newData]);
    setCount(count + 1);
  };

  return (
    <div>
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['생산계획 코드', '품명', '주문일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'생산계획 등록'} onClick={handleAdd} />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div style={{ paddingRight: '20px' }}>
          <BasicTable dataSource={dataSourceOne} defaultColumns={defaultColumnsOne} setDataSource={setDataSourceOne} />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px' }}>
            <h2 className='bordered-box-title'>월별 생산 계획</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable dataSource={dataSourceTwo} defaultColumns={defaultColumnsTwo} setDataSource={setDataSourceTwo} />
        </div>
      </div>
    </div>
  )
}

export default ProductManagement
