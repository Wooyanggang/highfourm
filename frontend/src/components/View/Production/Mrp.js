import React, { useState } from 'react';
import Container from '../../Common/Container';
import { SearchInput, SearchSelectBox, BtnFilter } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';

const Mrp = () => {

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
      title: '납기일',
      dataIndex: 'name',
      width: '20%',
    },
    {
      title: '생산계획 코드',
      dataIndex: 'age',
    },
    {
      title: '품번',
      dataIndex: 'address',
    },
    {
      title: '품명',
      dataIndex: 'operation',
    },
    {
      title: '계획 수량',
      dataIndex: 'operation',
    },
  ];

  const defaultColumnsTwo = [
    {
      title: '자재명',
      dataIndex: 'name',
      width: '20%',
    },
    {
      title: '자재 코드',
      dataIndex: 'age',
    },
    {
      title: '단위',
      dataIndex: 'address',
    },
    {
      title: '총 소요 수량',
      dataIndex: 'operation',
    },
    {
      title: '현 재고',
      dataIndex: 'operation',
    },
    {
      title: '안전 재고',
      dataIndex: 'operation',
    },
    {
      title: '입고 예정량',
      dataIndex: 'operation',
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
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>생산계획 상세</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable dataSource={dataSource} defaultColumns={defaultColumnsOne} setDataSource={setDataSource} />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>자재소요 계획</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable dataSource={dataSource} defaultColumns={defaultColumnsTwo} setDataSource={setDataSource} />
        </div>
      </div>
    </div>
  )
}

export default Mrp
