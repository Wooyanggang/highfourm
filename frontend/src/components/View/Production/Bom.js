import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import PagingTable from '../../Common/Table/PagingTable';
import { Button, Popconfirm } from 'antd';
import { BtnBlack, SearchInput } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';

function Bom() {
  // const [loading, setLoading] = useState(true);
  const [countTest, setCountTest] = useState(0);
  const [countProduct, setCountProduct] = useState(0);
  const [dataTest, setDataTest] = useState([]);
  const [dataProduct, setDataProduct] = useState([]);
  
  useEffect(() => {
    const dataUrl = './data.json';
    
    fetch(dataUrl)
    .then(response => response.json())
    .then(result => {
        setDataTest(result["test"]);
        setDataProduct(result["product"]);
        // setLoading(false);
        setCountTest(result["test"].length);
        setCountTest(result["product"].length);
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류 발생:', error);
        // setLoading(false);
      });
  }, []);
    
  const handleDeleteTest = (key) => {
    setDataTest((prevState) => prevState.filter((item) => item.key !== key));
  };
  const handleDeleteProduct = (key) => {
    setDataProduct((prevState) => prevState.filter((item) => item.key !== key));
  };
  
  const handleAddTest = () => {
    const newData = {
      key: countTest,
      // name: '',
      // age: '',
      // address: '',
    };
    setDataTest(prevState => [ ...prevState, newData ]);
    setCountTest(countTest + 1);
  };
  const handleAddProduct = () => {
    const newData = {
      key: countProduct,
      // name: '',
      // age: '',
      // address: '',
    };
    setDataProduct(prevState => [ ...prevState, newData ]);
    setCountProduct(countProduct + 1);
  };

  const testColumns = [
    {
      title: 'name',
      dataIndex: 'name',
      width: '30%',
      editable: true,
    },
    {
      title: 'age',
      dataIndex: 'age',
      editable: true,
    },
    {
      title: 'address',
      dataIndex: 'address',
      editable: true,
    },
    {
      title: 'operation',
      dataIndex: 'operation',
      render: (_, record) =>
        dataTest.length >= 1 ? (
          <Popconfirm title="정말 삭제하시겠습니까?" onConfirm={() => handleDeleteTest(record.key)}>
            <a>삭제</a>
          </Popconfirm>
        ) : null,
    },
  ];
  const productColumns = [
    {
      title: '제품 코드',
      dataIndex: 'productId',
      width: '30%',
      editable: true,
    },
    {
      title: '상품명',
      dataIndex: 'productName',
      editable: true,
    },
    {
      title: '단위',
      dataIndex: 'unit',
      editable: true,
    },
    {
      title: '삭제',
      dataIndex: 'operation',
      render: (_, record) =>
        dataTest.length >= 1 ? (
          <Popconfirm title="정말 삭제하시겠습니까?" onConfirm={() => handleDeleteProduct(record.key)}>
            <a>삭제</a>
          </Popconfirm>
        ) : null,
    },
  ];
  // if (loading) {
  //   return <p>Loading...</p>; // 데이터 로딩 중일 때 표시할 내용
  // }

  return (
    <div className='bom-page'>
        <PageTitle value={'제품별 공정/소요자재 관리'}/>
        <SearchInput></SearchInput>
        <div className='add-btn'>
          <BtnBlack value={"등록"}></BtnBlack>
        </div>
        <Button
          onClick={handleAddTest}
          type="primary"
          style={{
            // marginBottom: 16,
          }}
        >
          행 추가
        </Button>
        <BasicTable 
        dataSource={dataTest} 
        defaultColumns={testColumns} 
        setDataSource={setDataTest} 
        />
        <Button
          onClick={handleAddProduct}
          type="primary"
          style={{
            // marginBottom: 16,
          }}
        >
          행 추가
        </Button>
        <PagingTable 
        dataSource={dataProduct} 
        defaultColumns={productColumns} 
        setDataSource={setDataProduct} 
        />
        
    </div>
  );
}

export default Bom