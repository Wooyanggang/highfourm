import React, { useEffect, useState } from 'react';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';

const UserList = () => {
  const [dataSource, setDataSource] = useState([
    {
      key: '',
      user_name: '',
      emp_no: '',
      email: '',
      register_state: '',
      delete: '',
    },
  ]);

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await axios.get('/users')
        
        const userData = await res.data.map((rowData) => ({
          key: rowData.user_no,
          user_name: rowData.user_name,
          emp_no: rowData.emp_no,
          email: rowData.email,
          register_state: rowData.register_state,
        }))
        
        setDataSource(dataSource.concat(userData))
      } catch(e) {
        console.error(e.message)
      }
    }
}, []);

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }

  const defaultColumns = [
    {
      title: '사원명',
      dataIndex: 'user_name',
      width: '20%',
      // render: (text) => <a href={`/users/edit/${user_no}`}>{text}</a>,
      render: (text) => <a href='/users/edit/'>{text}</a>,
    },
    {
      title: '사번',
      dataIndex: 'emp_no',
      // render: (text) => <a href={`/users/edit/${user_no}`}>{text}</a>,
      render: (text) => <a href='/users/edit/'>{text}</a>,
    },
    {
      title: '계정 주소',
      dataIndex: 'email',
    },
    {
      title: '가입 여부',
      dataIndex: 'register_state',
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
      <PageTitle value={'사용자 관리'} />
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', marginBottom: '24px' }}>
        <SearchSelectBox selectValue={['사원명', '사번', '계정 주소', '가입 여부']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
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
