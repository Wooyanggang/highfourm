import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';
import { calc } from 'antd/es/theme/internal';
import KeyTable from '../../Common/Table/KeyTable';
import { useNavigate, useParams } from 'react-router-dom';

function Bom() {
  const navigate = useNavigate();
  const { productId } = useParams();
  const [dataProduct, setDataProduct] = useState([]);
  const [dataProcess, setDataProcess] = useState([]);
  const [dataRequiredMaterial, setDataRequiredMaterial] = useState([]);
  
  useEffect(() => {
    let currentUrl = '/bom';
    if (productId) {currentUrl = `/bom/detail/${productId}`}
    fetch(`/bom/detail/${productId}`)
    // const currentUrl = '/bom';
    fetch(currentUrl)
    .then(response => response.json())
    .then(result => {
      if (result["product"]) {
        const newData = result["product"].map((item, index) => ({ key: index, ...item }));
        setDataProduct(newData);
      } else{
        setDataProduct(result["product"]);
      }
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류 발생:', error);
      });
  }, []);

  const defaultColumnsProduct = [
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

  const defaultColumnsProcess = [
    {
      title: '공정 코드',
      dataIndex: 'productId',
    },
    {
      title: '공정 순서',
      dataIndex: 'sequence',
    },
    {
      title: '공정명',
      dataIndex: 'processName',
    },
    {
      title: '시간 단위',
      dataIndex: 'timeUnit',
    },
    {
      title: '표준 작업 시간',
      dataIndex: 'standardWorkTime',
    },
    {
      title: '산출물 단위',
      dataIndex: 'outputUnit',
    },
  ];

  const defaultColumnsRequiredMaterial = [
    {
      title: '원자재 코드',
      dataIndex: 'materialId',
    },
    {
      title: '투입 공정',
      dataIndex: 'inputProcess',
    },
    {
      title: '자재명',
      dataIndex: 'materialName',
    },
    {
      title: '투입량',
      dataIndex: 'inputAmount',
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
      <h3 style={{marginBottom:'12px'}}>제품 검색</h3>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', alignItems: 'center' }}>
        <SearchSelectBox selectValue={['제품 코드', '제품명']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div className='add-btn'>
        <BtnBlack value={"항목 추가"} type="primary" />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div className='table-box'>
          <KeyTable
          dataSource={dataProduct} 
          defaultColumns={defaultColumnsProduct} 
          setDataSource={setDataProduct}
          pagination={false}
          keyName="productId"
          />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ flexWrap: 'wrap' }}>
            <h3 className='bordered-box-title'>공정 조회/설정</h3>
            <hr className='box-title-line' />
          </div>
          <div style={{ minHeight: '240px' }}>
            <BasicTable 
            dataSource={dataProcess} 
            defaultColumns={defaultColumnsProcess} 
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
            dataSource={dataRequiredMaterial} 
            defaultColumns={defaultColumnsRequiredMaterial} 
            setDataSource={setDataRequiredMaterial} 
            pagination={false}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Bom