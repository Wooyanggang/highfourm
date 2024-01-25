import React, { useEffect, useState } from 'react';
import Container from '../../Common/Container';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import axios from 'axios';
import { response } from 'express';

const UserList = () => {

  const baseUrl = "http://localhost.3000"
  const [user_name, setUser_name] = useState();
  const [user_number, setUser_number] = useState();
  const [email, setEmail] = useState();
  const [accept, setAccept] = useState();

  useEffect(() => {
    getUser()
  }, []);

  async function getUser() {
    await axios
      .get(baseUrl + "/users")
      .then((res) => {
        console.log(response.data);
        setUser_name(response.data.user_name);
        setUser_number(response.data.user_number);
        setEmail(response.data.email);
        if (response.data.register_state === 'Y') {
          setAccept('등록');
        } else {
          setAccept('대기');
        }
      })
      .catch((error) => {
        console.log(error);
      })
  }

  const [dataSource, setDataSource] = useState([
    // {
    //   key: {user_number},
    //   user_name: {user_name},
    //   user_number: {user_number},
    //   email: {email},
    //   accept: {accept},
    // },
    {
      key: '0',
      user_name: '박보검',
      user_number: '1000',
      email: 'park33',
      accept: '등록',
    },
    {
      key: '2',
      user_name: '홍길동',
      user_number: '1001',
      email: 'hong123',
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
      title: '등록 여부',
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
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', marginBottom: '24px' }}>
        <SearchSelectBox selectValue={['사원명', '계정 주소']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'사용자 등록'} onClick={onClick} />
      </div>
      <div style={{ width: '720px' }}>
        <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
      </div>
    </div>
  )
}

export default UserList
