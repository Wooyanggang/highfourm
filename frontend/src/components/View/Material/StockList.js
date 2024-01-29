import { Modal } from 'antd';
import React, { useState } from 'react';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import StockNew from './StockNew';
import PageTitle from '../../Common/PageTitle';

const StockList = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [dataSource, setDataSource] = useState([
    {
      key: '0',
      name: 'Edward King 0',
      age: 'London, Park Lane no. 1',
      address: 'g',
    },
    {
      key: '1',
      name: 'Edward King 1',
      age: 'London, Park Lane no. 132',
      address: 'kg',
    },
  ]);

  const defaultColumnsOne = [
    {
      title: '자재 코드',
      dataIndex: 'name',
      width: '20%',
    },
    {
      title: '자재명',
      dataIndex: 'age',
      editable: true,
    },
    {
      title: '단위',
      dataIndex: 'address',
      editable: true,
    },
    {
      title: '총재고량',
      dataIndex: 'operation',
      editable: true,
    },
    {
      title: '재고관리 방식',
      dataIndex: 'operation',
      editable: true,
    },
    {
      title: '안전재고',
      dataIndex: 'operation',
      editable: true,
    },
    {
      title: '최대재고',
      dataIndex: 'operation',
      editable: true,
    },
    {
      title: 'LeadTime',
      dataIndex: 'operation',
      editable: true,
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

  //등록 버튼 클릭 시 모달
  const showModal = () => {
    setIsModalOpen(true);
    document.body.style.overflow = 'hidden';
  };

  const handleOk = () => {
    document.getElementById("stockNewForm").submit();
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <div>
      <PageTitle value={'재고 현황 조회'}/>
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['자재코드', '자재명', '재고관리 방식']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'원자재 등록'} onClick={showModal} />
        <Modal
          title='원자재 등록'
          open={isModalOpen}
          onOk={handleOk}
          onCancel={handleCancel}
          okText='저장'
          cancelText='취소'
          width='40%'
        >
          <StockNew  formAction='/materials/stock' />
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