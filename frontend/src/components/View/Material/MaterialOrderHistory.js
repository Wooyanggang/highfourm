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
      title: '발주코드',
      dataIndex: 'materialHistoryId',
      render: (text) => <a href={`/users/edit/${dataSource.empNo}`}>{text}</a>,
    },
    {
      title: '발주일',
      dataIndex: 'orderDate',
    },
    {
      title: '입고일',
      dataIndex: 'recievingDate ',
    },
    {
      title: '자재코드',
      dataIndex: 'materialId ',
    },
    {
      title: '자재명',
      dataIndex: 'materialName',
    },
    {
      title: '규격/사양',
      dataIndex: 'standard',
    },
    {
      title: '단위',
      dataIndex: 'unit',
    },
    {
      title: '공급처',
      dataIndex: 'supplier',
    },
    {
      title: '이월재고량',
      dataIndex: 'restStock',
    },
    {
      title: '재고량',
      dataIndex: 'materialInventory ',
    },
    {
      title: '사용량',
      dataIndex: 'usedAmount',
    },
    {
      title: '입고량',
      dataIndex: 'inboundAmount ',
    },
    {
      title: '발주량',
      dataIndex: 'orderAmount',
    },
    {
      title: '입고 단가',
      dataIndex: 'unitPrice',
    },
    {
      title: '금액',
      dataIndex: 'totalPrice',
    },
    {
      title: '비고',
      dataIndex: 'note',
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
  const onClick = () => {
    window.location.href = '/materials/order-history/new'
  }

  return (
    <div>
      <PageTitle value={'원자재 관리 < 수급 내역 관리'}/>
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['자재코드', '자재명', '발주일', '입고일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'수급내역 등록'} onClick={onClick} />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column' }}>
        <div style={{ border: '1px solid #d9d9d9', padding: '30px 35px', marginBottom: '20px' }}>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <h2 style={{ fontSize: '16px', display: 'inline-block' }}>입고대기</h2>
            <hr style={{ color: '#000', width: '520px', marginLeft: '20px' }} />
          </div>
          <div style={{ marginTop: '20px', height: '300px', overflowY: 'auto' }}>
            <BasicTable dataSource={dataSource} defaultColumns={defaultColumnsOne} setDataSource={setDataSource} />
          </div>
        </div>
        <div style={{ border: '1px solid #d9d9d9', padding: '30px 35px', marginBottom: '20px' }}>
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
