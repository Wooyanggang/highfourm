import React, { useState } from 'react';
import { UploadOutlined, AudioOutlined } from '@ant-design/icons';
import { Input, Space, } from 'antd';
import axios from 'axios';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Dropdown, message, Tooltip, Popconfirm, Table, FLex, Upload } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import '../../../App.css'
import PageTitle from '../../Common/PageTitle';

const OrderList = () => {

  const [dataSource, setDataSource] = useState([]);
  const [count, setCount] = useState(dataSource.length);

  const handleDelete = (key) => {
    console.log(key);
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  };

  const handleAdd = () => {
    const newData = {
      key: count,
      productName: `품명`,
      amount: 0,
      unitPrice: 0,
      price: 0,
    };
    setDataSource([...dataSource, newData]);
    setCount(count + 1);
  };

  const props = {
    action: 'https://run.mocky.io/v3/435e224c-44fb-4773-9faf-380c5e6a2188',
    onChange({ file, fileList }) {
      if (file.status !== 'uploading') {
        console.log(file, fileList);
      }
    },
    defaultFileList: [
      {
        uid: '1',
        name: '나주상사주문서.pdf',
        status: 'uploading',
        url: 'http://www.baidu.com/xxx.png',
        percent: 33,
      },
    ],
  };

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
      render: (_, record) => `${record.amount * record.unitPrice}`,
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

  const orderNewformSubmit = () => {
    const formData = new FormData(document.getElementById('orderNewForm'));

    const jsonData = {};
    formData.forEach((value, key) => {
      jsonData[key] = value;
    });
    const today = new Date();
    // 예제: MaterialRequestDTO와 일치하도록 구성
    const ordersNewRequest = {
      orderId: `${today.getFullYear()}${today.getMonth() + 1}${today.getDate()}`,
      vendor: jsonData.vendor,
      manager: jsonData.manager,
      orderDate: jsonData.orderDate,
      dueDate: jsonData.dueDate,
      endingState: false,
    };

    axios.post('/orders/new', JSON.stringify(ordersNewRequest),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then(response => {
        console.log('orders added successfully');
      })
      .catch(error => {
        console.log(jsonData)
        console.error('Error adding orders:', error);
      });

  };

  return (
    <>
      <PageTitle value={'주문 등록'} />
      <div style={{ width: '750px' }}>
        <div className='order-new-page'>
          <form action='' id='orderNewForm' className='input-form'>
            <div className='input-wrap'>
              <div className='input'>
                <label htmlFor="vendor">거래처명</label>
                <InputBar placeholderMsg={'거래처명'} name={'vendor'} id={'vendor'} />
              </div>
              <div className='input'>
                <label htmlFor="manager">담당자</label>
                <InputBar placeholderMsg={'manager'} name={'manager'} id={'manager'} />
              </div>
              <div className='input'>
                <label htmlFor="orderDate">주문일</label>
                <InputBar placeholderMsg={'orderDate'} name={'orderDate'} inputId={'orderDate'} />
              </div>
              <div className='input'>
                <label htmlFor="dueDate">납기일</label>
                <InputBar placeholderMsg={'dueDate'} name={'dueDate'} inputId={'dueDate'} />
              </div>
            </div>
            <div className='order-new-table'>
              <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
            </div>
            <div className='order-new-btn'>
              <div className='order-new-add-btn'>
                <BtnBlack value={'제품 추가'} onClick={handleAdd} />
              </div>
              <div className='order-new-cancel-btn'>
                <BtnWhite value={'취소'} />
              </div>
              <div className='order-new-submit-btn'>
                <BtnBlue value={'주문 등록'} onClick={orderNewformSubmit} />
              </div>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};


export default OrderList;