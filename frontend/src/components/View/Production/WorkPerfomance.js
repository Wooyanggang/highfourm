import React, { useState } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { Input, Popconfirm } from "antd";
import { BtnBlack, InputBar } from '../../Common/Module';

function WorkPerfomance() {
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
  
  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }
  
  const defaultColumns = [
    {
      title: 'name',
      dataIndex: 'name',
      width: '30%',
      editable: true,
    },
    {
      title: 'age',
      dataIndex: 'age',
    },
    {
      title: 'address',
      dataIndex: 'address',
    },
    {
      title: 'operation',
      dataIndex: 'operation',
      render: (_, record) =>
        dataSource.length >= 1 ? (
          <Popconfirm title="Sure to delete?" onConfirm={() => handleDelete(record.key)}>
            <a>Delete</a>
          </Popconfirm>
        ) : null,
    },
  ];

  return (
    <div className='work-perfomance-page'>
        <form action='' className='search-form'>
          <div className='search-input-wrap'>
            <div className='search-input'>
              <label for='workDate'>작업 일자</label>
              <Input type='date' id='workDate' size='large'></Input>
            </div>
            <div className='search-input'>
              <label for='product-management-id'>생산 계획 번호</label>
              <InputBar id={'product-management-id'}></InputBar>
            </div>
          </div>
          <div className='search-input-wrap'>
            <div className='search-input'>
              <label for='manager'>담당자</label>
              <InputBar id={'manager'}></InputBar>
            </div>
            <div className='search-input'>
              <label for='product-name'>생산품명</label>
              <InputBar id={'product-name'}></InputBar>
            </div>
          </div>
        </form>
        <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
    </div>
  );
}

export default WorkPerfomance