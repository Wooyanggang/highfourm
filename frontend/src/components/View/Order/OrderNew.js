import React, { useState } from 'react';
import { AudioOutlined } from '@ant-design/icons';
import { Input, Space } from 'antd';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Dropdown, message, Tooltip, Popconfirm, Table, FLex } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import '../../../App.css'
const { Search } = Input;
const suffix = (
  <AudioOutlined
    style={{
      fontSize: 16,
      color: '#1677ff',
    }}
  />
);

const OrderList = () => {
  const [dataSource, setDataSource] = useState([]);

  const addOrderProduct = () => {
    setDataSource([...dataSource, { productName: 'ㅇㅇ', amount: '', unitPrice: '' }]);
    console.log('클릭됨');
    console.log('dataSource: ' + dataSource);
    console.log('dataSource: ' + dataSource[dataSource.length -1]);
  }

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }

  const defaultColumns = [
    {
      title: '제품명',
      dataIndex: 'productName',
      width: '250px',
      editable: true,
    },
    {
      title: '수량',
      dataIndex: 'amount',
      editable: true,
    },
    {
      title: '단가(원)',
      dataIndex: 'unitPrice',
      editable: true,
    },
    {
      title: '합(원)',
      dataIndex: 'price',
    },
    {
      title: '삭제',
      dataIndex: 'operation',
      render: (_, record) =>
        dataSource.length >= 1 ? (
          <Popconfirm title="삭제하시겠습니까?" onConfirm={() => handleDelete(record.key)}>
            <a>Delete</a>
          </Popconfirm>
        ) : null,
    },
  ];
  return (
    <>
      <div style={{ width: '750px' }}>
        <div className='work-perfomance-page'>
          <form action='' className='search-form'>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label for="vender">거래처명</label>
                <InputBar placeholderMsg={'거래처명'} id={'vender'} />
              </div>
              <div className='search-input'>
                <label for="manager">담당자</label>
                <InputBar placeholderMsg={'manager'} id={'manager'} />
              </div>
            </div>
            <div className='search-input-wrap'>
              <div className='search-input'>
                <label for="orderDate">주문일</label>
                <InputBar placeholderMsg={'orderDate'} inputId={'orderDate'} />
              </div>
              <div className='search-input'>
                <label for="dueDate">납기일</label>
                <InputBar placeholderMsg={'dueDate'} inputId={'dueDate'} />
              </div>
            </div>
          </form>
          <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
          <BtnBlack value={'제품 추가'} onClick={() => addOrderProduct()} />
        </div>
      </div>
    </>
  )
}

export default OrderList;