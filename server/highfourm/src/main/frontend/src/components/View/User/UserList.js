import React, { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';
import KeyTable from '../../Common/Table/KeyTable';

const UserList = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [dataSource, setDataSource] = useState([]);
  const [searchType, setSearchType] = useState('사원명');
  const currentURL = window.location.pathname;

  useEffect(() => {
    async function fetchData() {
      try {
        let res;

        if (currentURL === '/users/search') {
          const searchParams = new URLSearchParams(location.search);
          const searchTypeParam = searchParams.get('searchType');
          const searchValueParam = searchParams.get('search');

          res = await axios.get('/users/search', {
            params: {
              searchType: searchTypeParam,
              search: searchValueParam,
            },
          });
        } else {
          res = await axios.get('/users');
        }

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
        console.error(e);
      }
    }
    fetchData();
  }, [currentURL, location.search]);

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);

    const deleteUserNo = key;
    let deleteUserInfo = dataSource.filter((item) => item.key === key);
    for (const name of deleteUserInfo) {
      deleteUserInfo = name.user_name;
    }

    axios({
      method: 'DELETE',
      url: `/users/delete/${deleteUserNo}`,
      data: JSON.stringify(deleteUserNo),
      headers: { 'Content-Type': 'application/json' },
    })
      .then(
        alert(`${deleteUserInfo} 사원이 삭제되었습니다.`),
      )
      .catch(e => console.error(e));
  }

  const defaultColumns = [
    {
      title: '사원명',
      dataIndex: 'user_name',
      width: '20%',
    },
    {
      title: '사번',
      dataIndex: 'emp_no',
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
    navigate(`/users/search?searchType=${encodeURIComponent(searchType)}&search=${encodeURIComponent(value)}`);
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
        <KeyTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} pagination={true} url='user/edit' keyName='key' />
      </div>
    </div>
  )
}

export default UserList
