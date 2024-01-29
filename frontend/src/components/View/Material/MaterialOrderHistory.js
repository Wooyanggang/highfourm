import React, { useState } from 'react';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import { Popconfirm } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import PageTitle from '../../Common/PageTitle';

const MaterialOrderHistory = () => {

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
    {
      key: '2',
      name: 'Edward King 1',
      age: '32',
      address: 'London, Park Lane no. 1',
    },
  ]);

  const defaultColumnsOne = [
    {
      title: '발주일',
      dataIndex: 'name',
      width: '20%',
    },
    {
      title: '입고일',
      dataIndex: 'age',
    },
    {
      title: '자재코드',
      dataIndex: 'address',
    },
    {
      title: '자재명',
      dataIndex: 'operation',
    },
    {
      title: '규격/사양',
      dataIndex: 'operation',
    },
    {
      title: '단위',
      dataIndex: 'operation',
    },
    {
      title: '공급처',
      dataIndex: 'operation',
    },
    {
      title: '이월재고량',
      dataIndex: 'operation',
    },
    {
      title: '재고량',
      dataIndex: 'operation',
    },
    {
      title: '사용량',
      dataIndex: 'operation',
    },
    {
      title: '입고량',
      dataIndex: 'operation',
    },
    {
      title: '발주량',
      dataIndex: 'operation',
    },
    {
      title: '입고 단가',
      dataIndex: 'operation',
    },
    {
      title: '금액',
      dataIndex: 'operation',
    },
    {
      title: '비고',
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
      <PageTitle value={'원자재 관리 < 원자재 수급 내역 관리'}/>
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['자재코드', '자재명', '발주일', '입고일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'생산계획 등록'} onClick={handleAdd} />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column' }}>
        <div style={{ border: '1px solid #d9d9d9', padding: '40px 45px', marginBottom: '20px' }}>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <h2 style={{ fontSize: '16px', display: 'inline-block' }}>입고대기</h2>
            <hr style={{ color: '#000', width: '520px', marginLeft: '20px' }} />
          </div>
          <div style={{ marginTop: '20px', height: '300px', overflowY: 'auto' }}>
            <BasicTable dataSource={dataSource} defaultColumns={defaultColumnsOne} setDataSource={setDataSource} />
          </div>
        </div>
        <div style={{ border: '1px solid #d9d9d9', padding: '40px 45px', marginBottom: '20px' }}>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <h2 style={{ fontSize: '16px', display: 'inline-block' }}>입고완료</h2>
            <hr style={{ color: '#000', width: '520px', marginLeft: '20px' }
            } />
          </div>
          <div style={{ marginTop: '20px', height: '300px', overflowY: 'auto' }}>
            <BasicTable dataSource={dataSource} defaultColumns={defaultColumnsOne} setDataSource={setDataSource} />
          </div>
        </div>
      </div>

      {/* </ Container> */}
    </div>
  )
}

export default MaterialOrderHistory
