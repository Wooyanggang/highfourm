import React, { useEffect, useState } from 'react';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';

const UserList = () => {
  const [test, setTest] = useState();

  useEffect(() => {
    axios.get('/users')
      .then(res => setTest(res.data))
      .catch(error => console.log(error))
  }, [])

  const [dataSource, setDataSource] = useState([
    {
      key: '0',
      user_name: '박보검',
      user_number: '1000',
      email: 'park33@gmail.com',
      accept: '완료',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },


    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123@gmail.com',
      accept: '대기',
    },
  ]);

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }

  const defaultColumns = [
    {
      title: '사원명',
      dataIndex: 'user_name',
      width: '20%',
      // render: (text) => <a href={`/users/edit/${user_number}`}>{text}</a>,
      render: (text) => <a href='/users/edit/'>{text}</a>,
    },
    {
      title: '사번',
      dataIndex: 'user_number',
      // render: (text) => <a href={`/users/edit/${user_number}`}>{text}</a>,
      render: (text) => <a href='/users/edit/'>{text}</a>,
    },
    {
      title: '계정 주소',
      dataIndex: 'email',
    },
    {
      title: '가입 여부',
      dataIndex: 'accept',
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
    window.location.href = '/users/new'
  }

  return (
    <div>
      <PageTitle title={'사용자 관리'} />
      <div>{test}</div>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', marginBottom: '24px' }}>
        <SearchSelectBox selectValue={['사원명', '사번', '계정 주소', '가입 여부']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'사용자 등록'} onClick={onClick} />
      </div>
      <div style={{ width: '720px', height: '565px', overflowY: 'auto' }}>
        <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
      </div>
    </div>
  )
}

export default UserList
