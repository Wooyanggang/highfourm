import React, { useState } from 'react';
import Container from '../../Common/Container';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';

const ProductManagement = () => {

  const [dataSource, setDataSource] = useState([
    {
      key: '0',
      name: 'Edward King 0',
      age: '32',
      address: 'London, Park Lane no. 0',
    },
    {
      key: '1',
      name: 'Edward King 1',
      age: '32',
      address: 'London, Park Lane no. 1',
    },
  ]);

  const defaultColumnsOne = [
    {
      title: '계획 코드',
      dataIndex: 'name',
      width: '20%',
    },
    {
      title: '품명',
      dataIndex: 'age',
    },
    {
      title: '생산 단위',
      dataIndex: 'address',
    },
    {
      title: '주문 수량',
      dataIndex: 'operation',
    },
    {
      title: '주문일',
      dataIndex: 'operation',
    },
    {
      title: '착수일',
      dataIndex: 'operation',
    },
    {
      title: '납기일',
      dataIndex: 'operation',
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
      dataIndex: 'age',
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

  const [count, setCount] = useState(dataSource.length);
  const handleAdd = () => {
    const newData = {
      key: count,
      name: `Edward King ${count}`,
      age: '32',
      address: `London, Park Lane no. ${count}`,
    };
    setDataSource([...dataSource, newData]);
    setCount(count + 1);
  };

  return (
    <div>
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['생산계획 코드', '품명', '주문일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'생산계획 등록'} onClick={handleAdd} />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div style={{ paddingRight: '20px' }}>
          <BasicTable dataSource={dataSource} defaultColumns={defaultColumnsOne} setDataSource={setDataSource} />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px' }}>
            <h2 className='bordered-box-title'>월별 생산 계획</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable dataSource={dataSource} defaultColumns={defaultColumnsTwo} setDataSource={setDataSource} />
        </div>
      </div>
    </div>
  )
}

export default ProductManagement
