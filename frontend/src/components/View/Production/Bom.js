import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { Button, Popconfirm } from 'antd';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';

function Bom() {
  const [count, setCount] = useState(0);
  const [dataSource, setDataSource] = useState([
    {
      key: 0,
      productID: 'A001',
      productName: '밀키스',
      writeDate: '2024-01-01',
      updateDate: '2024-01-02',
    },
    {
      key: 1,
      productID: 'A002',
      productName: '봉봉',
      writeDate: '2024-01-01',
      updateDate: '2024-01-02',
    },
  ]);
  
  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }
  
  const handleAdd= () => {
    const newData = {
      key: count,
      // name: '',
      // age: '',
      // address: '',
    };
    setDataSource(prevState => [ ...prevState, newData ]);
    setCount(count + 1);
  };

  const defaultColumns = [
    {
      title: '제품 코드',
      dataIndex: 'productID',
      editable: true,
    },
    {
      title: '제품명',
      dataIndex: 'productName',
      editable: true,
    },
    // {
    //   title: '단위',
    //   dataIndex: '',
    //   editable: true,
    // },
    {
      title: '작성일',
      dataIndex: 'writeDate',
      editable: true,
    },
    {
      title: '수정일',
      dataIndex: 'updateDate',
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

  return (
    <div className='bom-page'>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', alignItems: 'center' }}>
        <SearchSelectBox selectValue={['제품 코드', '제품명']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div className='add-btn'>
        <BtnBlack value={"등록"} onClick={handleAdd} type="primary" />
      </div>
      <BasicTable 
      dataSource={dataSource} 
      defaultColumns={defaultColumns} 
      setDataSource={setDataSource} 
      />
    </div>
  );
}

export default Bom