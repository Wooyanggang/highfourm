import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Modal } from 'antd';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import StockNew from './StockNew';
import PageTitle from '../../Common/PageTitle';

const StockList = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [dataSource, setDataSource] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await axios.get('/materials/stock');

        const materialRequest = await res.data.map((rowData) => ({
          key: rowData.materialId,
          materialId: rowData.materialId,
          materialName: rowData.materialName,
          unit: rowData.unit,
          totalStock: rowData.totalStock,
          managementName: rowData.managementName,
          safetyStock: rowData.safetyStock,
          maxStock: rowData.maxStock,
          leadTime: rowData.LeadTime,
        }));
        setDataSource(materialRequest);
      } catch (e) {
        console.error(e.message);
      }
    }
    fetchData();
  }, []);

  const defaultColumnsOne = [
    {
      title: '자재 코드',
      dataIndex: 'materialId',
    },
    {
      title: '자재명',
      dataIndex: 'materialName',
    },
    {
      title: '단위',
      dataIndex: 'unit',
    },
    {
      title: '총재고량',
      dataIndex: 'totalStock',
    },
    {
      title: '재고관리 방식',
      dataIndex: 'managementName',
    },
    {
      title: '안전재고',
      dataIndex: 'safetyStock',
    },
    {
      title: '최대재고',
      dataIndex: 'maxStock',
    },
    {
      title: 'LeadTime',
      dataIndex: 'leadTime',
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

  //원자재 등록 버튼 클릭 시 모달 오픈
  const showModal = () => {
    setIsModalOpen(true);
    document.body.style.overflow = 'hidden';
  };

  const handleStockNewSubmit = () => {
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <div>
      <PageTitle value={'재고 현황 조회'} />
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['자재코드', '자재명', '재고관리 방식']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'원자재 등록'} onClick={showModal} />
        <Modal
          title='원자재 등록'
          open={isModalOpen}
          footer={null}
          onCancel={handleCancel}
          width='50%'
        >
          <StockNew onSubmit={handleStockNewSubmit} />
        </Modal>
      </div>
      <div style={{ width: '1200px', display: 'flex', gap: '10px', flexDirection: 'column' }}>
        <BasicTable
          dataSource={dataSource}
          defaultColumns={defaultColumnsOne}
          setDataSource={setDataSource}
        />
      </div>
    </div>
  )
};

export default StockList;