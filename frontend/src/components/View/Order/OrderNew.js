import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { UploadOutlined, AudioOutlined } from '@ant-design/icons';
import { Input, Space, Select, Form } from 'antd';
import axios from 'axios';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Dropdown, message, Tooltip, Popconfirm, Table, FLex, Upload } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import '../../../App.css'
import PageTitle from '../../Common/PageTitle';

const OrderList = () => {
  const navigate = useNavigate();

  const [dataSource, setDataSource] = useState([]);
  const [count, setCount] = useState(dataSource.length);
  const [productOptions, setProductOptions] = useState([]);
  const [ordersData, setOrdersData] = useState({
    vendor: '',
    manager: '',
    orderDate: '',
    dueDate: ''
  });

  useEffect(() => {
    axios.get('/orders/new')
      .then(response => {
        // response.data가 직접 리스트를 포함하고 있는지, 아니면 객체의 속성으로 리스트를 포함하고 있는지 확인
        const productList = response.data || [];
        const options = productList.map(productName => ({
          label: productName, // 여기서는 response.data가 직접 문자열 리스트라고 가정
          value: productName,
        }));
        setProductOptions(options);
        console.log(options);
      })
      .catch(error => {
        console.error('제품명 데이터를 불러오는데 실패했습니다.', error);
      });
  }, []);

  const handleInputChange = (e) => {
    setOrdersData({ ...ordersData, [e.target.name]: e.target.value });
  };

  const orderNewformSubmit = () => {
    const ordersNewRequest = {
      ...ordersData,
      orderId: null,
      endingState: false
    };

    const orderDetails = dataSource.map(item => ({
      orderId: null,
      productId: null,
      productAmount: item.amount,
      unitPrice: item.unitPrice,
      productName: item.productName
    }));

    axios.post('/orders/new', {
      orders: ordersNewRequest,
      orderDetails: orderDetails
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        alert('성공적으로 등록되었습니다.');
        navigate('/orders')
      })
      .catch(error => {
        alert('등록에 실패하였습니다.');
      });
  };

  const handleDelete = (key) => {
    console.log(key);
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  };

  const handleAdd = () => {
    const newData = {
      key: count, // 'key' 필드를 추가하여 각 행을 식별
      productName: '', // 초기 제품명 값 설정
      amount: 0,
      unitPrice: 0,
      price: 0,
    };
    setDataSource([...dataSource, newData]);
    setCount(count + 1);
  };

  const handleSelectChange = (value, key) => {
    // 선택된 제품명으로 dataSource 업데이트
    const newData = dataSource.map(item => {
      if (item.key === key) {
        return { ...item, productName: value };
      }
      return item;
    });
    setDataSource(newData);
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
      render: (text, record) => (
        <Form.Item name={`productName_${record.key}`} style={{ margin: 0 }}>
          <Select
            showSearch
            style={{ width: '100%' }}
            placeholder="제품명 선택"
            optionFilterProp="children"
            options={productOptions}
            filterOption={(input, option) =>
              option.label.toLowerCase().includes(input.toLowerCase())
            }
            onChange={(value) => handleSelectChange(value, record.key)}
          />
         </Form.Item>
      ),
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

  return (
    <>
      <PageTitle value={'주문 등록'} />
      <div style={{ width: '750px' }}>
        <div className='order-new-page'>
          <form action='' id='orderNewForm' className='input-form'>
            <div className='input-wrap'>
              <div className='input'>
                <label htmlFor="vendor">거래처명</label>
                <InputBar placeholderMsg={'거래처명'} name={'vendor'} id={'vendor'} onChange={handleInputChange} />
              </div>
              <div className='input'>
                <label htmlFor="manager">담당자</label>
                <InputBar placeholderMsg={'manager'} name={'manager'} id={'manager'} onChange={handleInputChange} />
              </div>
              <div className='input'>
                <label htmlFor="orderDate">주문일</label>
                <InputBar placeholderMsg={'orderDate'} type={'date'} name={'orderDate'} inputId={'orderDate'} onChange={handleInputChange} />
              </div>
              <div className='input'>
                <label htmlFor="dueDate">납기일</label>
                <InputBar placeholderMsg={'dueDate'} type={'date'} name={'dueDate'} inputId={'dueDate'} onChange={handleInputChange} />
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
                <BtnWhite value={'취소'} onClick={e => navigate('/orders')} />
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