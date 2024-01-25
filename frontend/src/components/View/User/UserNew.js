import React from 'react';
import { useNavigate } from 'react-router-dom';
import Container from '../../Common/Container';
import { BtnBlue, BtnWhite, InputBar } from '../../Common/Module';

const UserNew = () => {
  const navigate = useNavigate();

  const goBackNavigate = () => {
    navigate(-1)
  }

  return (
    <div>
      <form method='post' action='/users'>
        <div className='flex-line'>
          <div className='flex-div'>
            <label for='user_number' className='label-title'>사번</label>
            <InputBar inputId={'user_number'} id={'user_number'} placeholderMsg={'사번'} />
          </div>
          <div className='flex-div'>
            <label for='user_name' className='label-title'>이름</label>
            <InputBar inputId={'user_name'} id={'user_name'} placeholderMsg={'이름'} />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label for='position' className='label-title'>직급</label>
            <InputBar inputId={'position'} id={'position'} placeholderMsg={'직급'} />
          </div>
          <div className='flex-div'>
            <label for='birth_date' className='label-title'>생년월일</label>
            <InputBar inputId={'birth_date'} id={'birth_date'} placeholderMsg={'생년월일'} />
          </div>
        </div>
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label for='email' className='label-title'>이메일 주소</label>
          <InputBar inputId={'email'} id={'email'} placeholderMsg={'이메일 주소'} />
          <span className='email'>@gmail.com</span>
        </div>
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label for='emailCheck' className='label-title'>이메일 주소 확인</label>
          <InputBar inputId={'emailCheck'} id={'emailCheck'} placeholderMsg={'이메일 주소 확인'} />
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
