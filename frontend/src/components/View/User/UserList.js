import React from 'react'
import Container from '../../Common/Container'
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module'
import BasicTable from '../../Common/Table'

const UserList = () => {

  const SelectChangeHandler = (value) => {
    console.log(`selected ${value}`);
    // select 값 선택시 기능 구현 핸들러
  };

  const onSearch = (value, _e, info) => {
    // search 값 기능 구현 함수
    console.log(info?.source, value);
  }

  return (
    <div>
      <Container title={'사용자 관리'}>
        <SearchSelectBox selectValue={['사원명', '계정 주소']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
        <BtnBlack value={'사용자 등록'} link={'/users/new'} />
        <BasicTable></BasicTable>
      </ Container>
    </div>
  )
}

export default UserList
