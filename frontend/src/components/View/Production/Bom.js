import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';
import { calc } from 'antd/es/theme/internal';

function Bom() {
  const [countProduct, setCountProduct] = useState(0);
  const [countProcess, setCountProcess] = useState(0);
  const [countMaterial, setCountMaterial] = useState(0);
  const [dataProduct, setDataProduct] = useState([]);
  const [dataProcess, setDataProcess] = useState([]);
  const [dataMaterial, setDataMaterial] = useState([]);
  
  useEffect(() => {
    fetch('/bom')
    .then(response => response.json())
    .then(result => {
        const newData = result["product"].map((item, index) => ({ key: index, ...item }));
        setDataProduct(newData);
        setCountProduct(newData.length);
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류 발생:', error);
      });
  }, []);
  
  const handleAdd= () => {
    const newData = {
      key: countProduct,
    };
    setDataProduct(prevState => [ ...prevState, newData ]);
    setCountProduct(countProduct + 1);
  };

  const defaultColumns = [
    {
      title: '제품 코드',
      dataIndex: 'productId',
    },
    {
      title: '제품명',
      dataIndex: 'productName',
    },
    {
      title: '작성일',
      dataIndex: 'writeDate',
    },
    {
      title: '수정일',
      dataIndex: 'updateDate',
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

  return (
    <div className='bom-page'>
      <PageTitle value={'제품별 공정/소요자재 관리'}/>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', alignItems: 'center' }}>
        <SearchSelectBox selectValue={['제품 코드', '제품명']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div className='add-btn'>
        <BtnBlack value={"등록"} onClick={handleAdd} type="primary" />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div className='table-box'>
          <BasicTable 
          dataSource={dataProduct} 
          defaultColumns={defaultColumns} 
          setDataSource={setDataProduct}
          pagination={false}
          />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ flexWrap: 'wrap' }}>
            <h3 className='bordered-box-title'>공정 조회/설정</h3>
            <hr className='box-title-line' />
          </div>
          <div style={{ minHeight: '240px' }}>
            <BasicTable 
            dataSource={dataProduct} 
            defaultColumns={defaultColumns} 
            setDataSource={setDataProduct} 
            pagination={false}
            />
          </div>
          <div className='bordered-box-title' style={{ marginTop:'40px',flexWrap: 'wrap' }}>
            <h3 className='bordered-box-title'>소요자재 조회/설정</h3>
            <hr className='box-title-line' />
          </div>
          <div style={{ minHeight: '240px' }}>
            <BasicTable 
            dataSource={dataProduct} 
            defaultColumns={defaultColumns} 
            setDataSource={setDataProduct} 
            pagination={false}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Bom