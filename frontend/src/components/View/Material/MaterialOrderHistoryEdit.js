import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { BtnBlue, BtnWhite, InputBar } from '../../Common/Module';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';

const MaterialOrderHistoryEdit = () => {
  const navigate = useNavigate();
  const [newUser, setNewUser] = useState(
    {
      empNo: '',
      userName: '',
      position: '',
      birth: '',
      email: '',
    });

  const onChange = (e) => {
    setNewUser({
      ...newUser,
      [e.target.name]: e.target.value
    });
  }

  const onClickSubmit = (e) => {
    console.log(newUser)

    axios({
      method: 'POST',
      url: '/users/new',
      data: JSON.stringify(newUser),
      headers: { 'Content-Type': 'application/json' },
    })
      .then((res) => {
        console.log(res);
        navigate('/users');
      })
      .catch((error) => console.log(error))
  }

  const goBackNavigate = () => {
    navigate(-1)
  }

  return (
    <div>
      <PageTitle value={'수급내역 수정'} />
      <form id='userForm' method='post' action='/materials/order-history/new'>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='empNo' className='label-title'>발주일</label>
            <InputBar type={'date'} id={'orderDate'} name={'orderDate'} placeholderMsg={'발주일'} onChange={onChange} required />
          </div>
          <div className='flex-div'>
            <label htmlFor='userName' className='label-title'>입고일</label>
            <InputBar type={'date'} id={'recievingDate '} name={'recievingDate '} placeholderMsg={'입고일'} onChange={onChange} required />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='userName' className='label-title'>자재코드</label>
            <InputBar id={'materialId '} name={'materialId'} placeholderMsg={'자재코드	'} onChange={onChange} required />
          </div>
          <div className='flex-div'>
            <label htmlFor='birth' className='label-title'>공급처</label>
            <InputBar id={'supplier'} name={'supplier'} placeholderMsg={'공급처'} onChange={onChange} required />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='position' className='label-title'>규격/사양</label>
            <InputBar id={'standard'} name={'standard'} placeholderMsg={'규격/사양'} onChange={onChange} required />
          </div>
          <div className='flex-div'>
            <label htmlFor='position' className='label-title'>발주량</label>
            <InputBar id={'orderAmount'} name={'orderAmount'} placeholderMsg={'발주량'} onChange={onChange} required />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='position' className='label-title'>입고량</label>
            <InputBar id={'inboundAmount'} name={'inboundAmount'} placeholderMsg={'입고량'} onChange={onChange} required />
          </div>
          <div className='flex-div'>
            <label htmlFor='birth' className='label-title'>입고단가</label>
            <InputBar id={'unitPrice'} name={'unitPrice'} placeholderMsg={'입고단가'} onChange={onChange} required />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='birth' className='label-title'>비고</label>
            <InputBar id={'note '} name={'note '} placeholderMsg={'비고'} onChange={onChange} required />
          </div>
        </div>
        <div className='flex-btn'>
          <BtnBlue type='submit' value={'수정하기'} onClick={onClickSubmit} />
          <BtnWhite value={'취소'} onClick={goBackNavigate} />
        </div>
      </form>
    </div>
  )
}

export default MaterialOrderHistoryEdit
