import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { BtnBlue, BtnWhite, InputBar } from '../../Common/Module';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';

const UserNew = () => {

  useEffect(() => {
    axios({
      method: 'POST',
      url: '/users/new',
      // data: JSON.stringify({name: 'info',
      // num: '0',}),
      data: JSON.stringify,
      headers: { 'Content-Type': 'application/json' },
    })
      .then((res) => console.log(res))
      .catch((error) => console.log(error))
  }, [])

  const navigate = useNavigate();

  const goBackNavigate = () => {
    navigate(-1)
  }

  return (
    <div>
      <PageTitle value={'사용자 등록'} />
      <form method='post' action='/users/new'>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='emo_no' className='label-title'>사번</label>
            <InputBar id={'emo_no'} name={'emo_no'} placeholderMsg={'사번'} />
          </div>
          <div className='flex-div'>
            <label htmlFor='user_name' className='label-title'>이름</label>
            <InputBar id={'user_name'} name={'user_name'} placeholderMsg={'이름'} />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='position' className='label-title'>직급</label>
            <InputBar id={'position'} name={'position'} placeholderMsg={'직급'} />
          </div>
          <div className='flex-div'>
            <label htmlFor='birth' className='label-title'>생년월일</label>
            <InputBar id={'birth'} name={'birth'} placeholderMsg={'생년월일'} />
          </div>
        </div>
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label htmlFor='email' className='label-title'>이메일 주소</label>
          <InputBar id={'email'} name={'email'} placeholderMsg={'이메일 주소'} />
          <span className='email'>@gmail.com</span>
        </div>
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label htmlFor='emailCheck' className='label-title'>이메일 주소 확인</label>
          <InputBar id={'emailCheck'} name={'emailCheck'} placeholderMsg={'이메일 주소 확인'} />
          <span className='email'>@gmail.com</span>
        </div>
        <div className='flex-btn'>
          <BtnBlue type='submit' value={'등록하기'} />
          <BtnWhite value={'취소'} onClick={goBackNavigate} />
        </div>
      </form>
    </div>
  )
}

export default UserNew
