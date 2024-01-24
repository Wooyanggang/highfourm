import React, { useState } from 'react';
import Container from '../../Common/Container';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';

const UserList = () => {

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
      title: '사원명',
      dataIndex: 'name',
      width: '20%',
      // editable: true,
      // render: (text) => <a href='/users/edit/{empNo}'>{text}</a>,
      render: (text) => <a href='/users/edit/'>{text}</a>,
    },
    {
      title: '사원 ID',
      dataIndex: 'age',
      // render: (text) => <a href='/users/edit/{empNo}'>{text}</a>,
      render: (text) => <a href='/users/edit/'>{text}</a>,
    },
    {
      title: '계정 주소',
      dataIndex: 'address',
    },
    {
      title: '등록 여부',
      dataIndex: 'address',
    },
    {
      title: '삭제',
      dataIndex: 'delete',
      render: (_, record) =>
        dataSource.length >= 1 ? (
          <Popconfirm title="해당 사원을 삭제하시겠습니까?" onConfirm={() => handleDelete(record.key)}>
            <a>삭제</a>
          </Popconfirm>
        ) : null,
    },
  ];

  const SelectChangeHandler = (value) => {
    console.log(`selected ${value}`);
    // select 값 선택시 기능 구현 핸들러
  };

  const onSearch = (value, _e, info) => {
    // search 값 기능 구현 함수
    console.log(info?.source, value);
  }

  const onClick = () => {
    window.location.href='/users/new'
  }

  return (
    <div>
      {/* <Container title={'사용자 관리'}> */}
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', marginBottom: '24px'}}>
        <SearchSelectBox selectValue={['사원명', '계정 주소']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'사용자 등록'} onClick={onClick}/>
      </div>
      <div style={{ width: '720px' }}>
        <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
      </div>
      {/* </ Container> */}
    </div>
  )
}

export default UserList
