import React, { useState, useEffect, } from 'react';
import { Upload, Button, Modal } from 'antd';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';
import { BtnBlack, BtnFilter, SearchInput, SearchSelectBox } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import { UploadOutlined, AudioOutlined } from '@ant-design/icons';
import { useNavigate, useLocation, useParams } from 'react-router';

const onSearch = (value, _e, info) => console.log(info?.source, value);

const OrderList = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [orders, setOrders] = useState([]);
  const uploadUrl = 'http://13.125.172.114:9999/api/orders/new/upload';
  const searchParams = new URLSearchParams(location.search);
  const searchTypeParam = searchParams.get('searchType');
  const searchValueParam = searchParams.get('search');
  const [searchType, setSearchType] = useState('주문 번호');
  const currentURL = window.location.pathname;
  const [pdfFile, setPdfFile] = useState('');

  useEffect(() => {
    async function fetchData() {
      try {
        let res;

        if (currentURL === '/orders/search') {
          res = await axios.get('/api/orders/search', {
            params: {
              searchType: searchTypeParam,
              search: searchValueParam,
            },
          });
        } else {
          res = await axios.get('/api/orders');
        }

        const { orders, productNames } = res.data;
        const updatedOrders = orders.map(order => {
          const detailsWithProductName = order.orderDetails.map(detail => ({
            ...detail,
            productName: productNames[detail.productId]
          }));
          return { ...order, orderDetails: detailsWithProductName, key: order.orderId };
        });
        setOrders(updatedOrders);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
    if (pdfFile) {
      console.log('Current PDF file URL:', pdfFile);
    }
    fetchData();
  }, [location, searchTypeParam, searchValueParam, pdfFile]);

  const defaultColumns = [
    {
      title: '주문 번호',
      dataIndex: 'orderId',
    },
    {
      title: '거래처명',
      dataIndex: 'vendor',
    },
    {
      title: '담당자',
      dataIndex: 'manager',
    },
    {
      title: '품목',
      dataIndex: 'orderDetails',
      render: details => {
        console.log(details);
        if (details.length === 1) {
          return <span>{details[0].productName}</span>;
        } else if (details.length > 1) {
          return <span>{`${details[0].productName} 외 ${details.length - 1}건`}</span>;
        }
      }
    },
    {
      title: '주문일',
      dataIndex: 'orderDate',
    },
    {
      title: '납기일',
      dataIndex: 'dueDate',
    },
    {
      title: '금액',
      dataIndex: 'orderDetails',
      render: details => {
        const totalPrice = details.reduce((sum, detail) => {
          return sum + (detail.productAmount * detail.unitPrice);
        }, 0);
        return <span>{totalPrice.toLocaleString()}원</span>;
      }
    },
    {
      title: '상태',
      dataIndex: 'endingState',
      render: endingState => (endingState ? "완료" : "진행중")
    },
  ];

  const SelectChangeHandler = (value) => {
    setSearchType(value);
  };

  const onSearch = (value) => {
    navigate(`/orders/search?searchType=${encodeURIComponent(searchType)}&search=${encodeURIComponent(value)}`);
  }

  const props = {
    action: uploadUrl,
    onChange({ file, fileList }) {
      if (file.status !== 'uploading') {
        console.log(file, fileList);
      }
      if (file.status === 'done') {
        console.log(`${file.name} file uploaded successfully`);
        window.location.reload();
      } else if (file.status === 'error') {
        console.log(`${file.name} file upload failed.`);
      }
    },
  };

  return (
    <>
      <PageTitle value={'주문 관리'} />
      <div style={{ display: 'flex', gap: '10px 24px', alignItems: 'center' }}>
        <SearchSelectBox selectValue={['주문 번호', '거래처명', '담당자', '품목']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
      <div className='order-list-page'>
        <div className='order-list-wrap' style={{ width: '250px', display: 'flex' }}>
          <div className='order-new' style={{ marginRight: 'auto' }}>
            <BtnBlack value={'수동 등록'} onClick={() => window.location.href = '/orders/new'} />
          </div>
          <div style={{ verticalAlign: 'center' }}>
            <Upload {...props}>
              <Button icon={<UploadOutlined />}>PDF 등록</Button>
            </Upload>
          </div>
        </div>
      </div>
      <div className='.clickable-table tbody'>
        <BasicTable
          dataSource={orders}
          defaultColumns={defaultColumns}
          pagination={true}
        /*           onRowClick={(record, rowIndex, event) => {
                    console.log("Row clicked:", record, rowIndex);
                    handleRowClick(record)
                  }} */
        />
      </div>
    </>
  )
}
export default OrderList;
