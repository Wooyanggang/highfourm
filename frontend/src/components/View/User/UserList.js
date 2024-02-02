import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';

const UserList = () => {
  const navigate = useNavigate();
  const [dataSource, setDataSource] = useState([]);
  const [searchType, setSearchType] = useState('');

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await axios.get('/users');

        const userData = await res.data.map((rowData) => ({
          key: rowData.userNo,
          user_no: rowData.userNo,
          user_name: rowData.userName,
          emp_no: rowData.empNo,
          email: rowData.email,
          register_state: rowData.registerState,
        }));
        setDataSource(userData);
      } catch (e) {
        console.error(e.message);
      }
    }
    fetchData();
  }, []);

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
    // const deleteUserNo = dataSource.filter((item) => console.log(item));
    // const deleteUserName = dataSource.filter((item) => item.userName);

    // axios({
    //   method: 'DELETE',
    //   url: `/users/delete/${deleteUserNo}`,
    //   data: JSON.stringify(deleteUserNo),
    //   headers: { 'Content-Type': 'application/json' },
    // })
    //   .then((response) => {
    //     console.log(response);
    //     alert(`${deleteUserName} 사원이 삭제되었습니다.`);
    //     navigate('/users');
    //   })
    //   .catch(e => console.log(e));
  }

  const defaultColumns = [
    {
      title: '사원명',
      dataIndex: 'user_name',
      width: '20%',
      render: (text, record) => <a href={`/users/edit/${record.user_no}`}>{text}</a>,
    },
    {
      title: '사번',
      dataIndex: 'emp_no',
      render: (text, record) => <a href={`/users/edit/${record.user_no}`}>{text}</a>,
    },
    {
      title: '이메일',
      dataIndex: 'email',
    },
    {
      title: '가입 여부',
      dataIndex: 'register_state',
      render: (text) => text === 'Y' ? '등록' : '대기'
    },
    {
      title: '삭제',
      dataIndex: 'delete',
      render: (_, record) =>
        dataSource != null ? (
          <Popconfirm title="해당 사원을 삭제하시겠습니까?" onConfirm={() => handleDelete(record.key)}>
            <a>삭제</a>
          </Popconfirm>
        ) : null,
    },
  ];

  const SelectChangeHandler = (value) => {
    setSearchType(value);
  };

  const onSearch = (value) => {
    axios({
      method: 'GET',
      url: '/users/search',
      params: {
        searchType: searchType, search: value,
      }
    })
      .catch(e => console.log(e));
  }

  const onClick = () => {
    window.location.href = '/users/new'
  }

  return (
    <div>
      <PageTitle value={'사용자 관리'} />
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', marginBottom: '24px' }}>
        <SearchSelectBox selectValue={['사원명', '사번', '이메일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'사용자 등록'} onClick={onClick} />
      </div>
      <div style={{ width: '720px', height: '565px' }}>
        <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} pagination={true} />
      </div>
    </div>
  )
}

export default UserList
