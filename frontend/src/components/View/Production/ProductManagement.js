import React from 'react'
import Container from '../../Common/Container'
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module'
import EditableRow from '../../Common/Table/EditableRow';
import BasicTable from '../../Common/Table/BasicTable';

const ProductManagement = () => {

  const SelectChangeHandler = (value) => {
    console.log(`selected ${value}`);
    // select 값 선택시 기능 구현 핸들러
    // 각 페이지에서 구현해주세요
  };

  const onSearch = (value, _e, info) => {
    // search 값 기능 구현 함수
    console.log(info?.source, value);
  }

  const onClick = () => {

  }

  return (
    <div>
      <Container title={'생산 계획 수립'}>
        <div>
          <SearchSelectBox selectValue={['생산계획 코드', '품명', '주문일']} SelectChangeHandler={SelectChangeHandler} />
          <SearchInput onSearch={onSearch} />
        </div>
        <div>
          <BtnBlack value={'생산계획 등록'} onClick={onClick} />
          <EditableRow></EditableRow>
        </div>
        <div style={{ border: '1px solid #d9d9d9' }}>
          <h2>월별 생산 계획</h2>
          <hr style={{ color: '#000' }} />
          <BasicTable></BasicTable>
        </div>

      </ Container>
    </div>
  )
}

export default ProductManagement
