import React, { useState, useEffect } from 'react';
import BasicTable from '../Common/Table/BasicTable';
import { Popconfirm } from 'antd';
import { BtnBlack, SearchInput } from '../Common/Module';

// !!!!!!!!!!! 배포전 삭제 필요 !!!!!!!!!!!
// table 레퍼런스/test 용도
function Test() {
  // 테이블 row 수를 카운트하는 state
  // 테이블 수 마다 구분해 선언 필요
  const [countTest, setCountTest] = useState(0);
  const [countProduct, setCountProduct] = useState(0);
  // 테이블에 보여줄 data를 저장하는 state
  // 테이블 수 마다 구분해 선언 필요
  const [dataTest, setDataTest] = useState([]);
  const [dataProduct, setDataProduct] = useState([]);
  
  useEffect(() => {
    const dataUrl = './data.json';
    // data.json 파일에서 data를 fetch
    // 데이터를 json 형식으로 다루게 구현했지만 현재는 이 데이터가 로컬 데이터라는 한계가 있음
    // 추후 백엔드에서 데이터를 가져오는 로직이 구현된다면 그에 맞춰 수정 필요
    fetch(dataUrl)
    .then(response => response.json())
    .then(result => {
        // 테이블 마다 필요한 data set을 각 state에 저장
        // 현재는 data set를 문자열 키값으로 구분
        // 추후 백엔드 데이터를 가져오는 방식에 따라 수정이 필요할 수 있음
        setDataTest(result["test"]);
        setDataProduct(result["product"]);
        // .length로 table row 수를 카운트하고 행 추가 핸들러에 쓰임
        setCountTest(result["test"].length);
        setCountProduct(result["product"].length);
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류 발생:', error);
      });
  }, []);
    
  // 행 삭제 핸들러, 테이블마다 따로 선언
  // 행 삭제 기능이 없는 테이블이라면 필요하지 않음
  // 행 삭제 컨펌창의 onclick으로 실행됨
  const handleDeleteTest = (key) => {
    setDataTest((prevState) => prevState.filter((item) => item.key !== key));
  };
  const handleDeleteProduct = (key) => {
    setDataProduct((prevState) => prevState.filter((item) => item.key !== key));
  };
  
  // 행 추가 핸들러, 테이블마다 따로 선언
  // 행 추가 기능이 없는 테이블이라면 필요하지 않음
  // 행 추가 버튼의 onclick으로 실행됨
  const handleAddTest = () => {
    const newData = {
      key: countTest,
    };
    setDataTest(prevState => [ ...prevState, newData ]);
    setCountTest(countTest + 1);
  };
  const handleAddProduct = () => {
    const newData = {
      key: countProduct,
    };
    setDataProduct(prevState => [ ...prevState, newData ]);
    setCountProduct(countProduct + 1);
  };

  // column 설정, 테이블마다 따로 선언
  const testColumns = [
    {
      // title: column의 이름으로 출력 됨
      title: 'name',
      // data Index: data set의 key값과 제대로 매칭되어야 함. column 속성
      dataIndex: 'name',
      // editable: true일 때 테이블 열 단위로 편집이 가능하도록 설정
      editable: true,
      // width: 테이블의 각 열 너비를 설정
      width: '30%',
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
      // 행 삭제 컨펌창을 render
      // render를 통해 테이블 안에 다른 요소를 보여줄 수 있음
      // Status.js 페이지의 테이블엔 StepBar요소를 render하는 예시가 있으니 참고
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

  return (
    <div className='test-page'>
        <SearchInput></SearchInput>
        <div className='add-btn'>
          {/* 행 추가 버튼. onClick에 행 추가 핸들러 연결 */}
          <BtnBlack value={"등록"} onClick={handleAddTest} type="primary"></BtnBlack>
        </div>
        <BasicTable 
        // dataSource, defaultCoulmns, setDataSource 속성은 필수
        // pagination 속성을 통해 페이지네이션 기능 여부 설정 가능, 미설정 시 디폴트값은 true
        dataSource={dataTest} 
        defaultColumns={testColumns} 
        setDataSource={setDataTest} 
        pagination={false}
        />
        <div className='add-btn'>
          <BtnBlack value={"등록"} onClick={handleAddProduct} type="primary"></BtnBlack>
        </div>
        <BasicTable 
        dataSource={dataProduct} 
        defaultColumns={productColumns} 
        setDataSource={setDataProduct}
        />
    </div>
  );
}

export default Test