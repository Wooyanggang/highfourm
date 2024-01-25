import React from 'react';
import { useNavigate } from 'react-router-dom';
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
            <InputBar inputId={'user_number'} id={'user_number'} value={'10001'} disabled={true} />
          </div>
          <div className='flex-div'>
            <label for='user_name' className='label-title'>이름</label>
            <InputBar inputId={'user_name'} id={'user_name'} value={'홍길동'} />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label for='position' className='label-title'>직급</label>
            <InputBar inputId={'position'} id={'position'} value={'사원'} />
          </div>
          <div className='flex-div'>
            <label for='birth_date' className='label-title'>생년월일</label>
            <InputBar inputId={'birth_date'} id={'birth_date'} value={'1990-01-01'} />
          </div>
        </div>
        <div className='flex-div' >
          <label for='email' className='label-title'>이메일 주소</label>
          <InputBar inputId={'email'} id={'email'} value={'hong123'} disabled={true} />
          <span className='email'>@gmail.com</span>
        </div>
        <p style={{ color: 'rgba(0, 0, 0, 0.45)', marginBottom: '40px' }}>사용자 이메일은 수정 불가합니다. 수정을 원할 시 정보를 삭제하고 재등록 해주세요.</p>
        <div className='flex-btn'>
          <BtnBlue type='submit' value={'수정하기'} />
          <BtnWhite value={'취소'} onClick={goBackNavigate} />
        </div>
      </form>
    </div>
  )
}

export default UserNew
