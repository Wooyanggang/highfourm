import React from 'react';
import { useNavigate } from 'react-router-dom';
import Container from '../../Common/PageTitle';
import { BtnBlue, BtnWhite, InputBar } from '../../Common/Module';

const UserNew = () => {
  const navigate = useNavigate();

  const goBackNavigate = () => {
    navigate(-1)
  }

  return (
    <div>
      {/* <Container title={'사용자 수정'}> */}
      <form method='post' action='/users'>
        <div className='flex-line'>
          <div className='flex-div'>
            <label for='empNo' className='label-title'>사번</label>
            <InputBar inputId={'empNo'} id={'empNo'} value={'10001'} disabled={true} />
          </div>
          <div className='flex-div'>
            <label for='name' className='label-title'>이름</label>
            <InputBar inputId={'name'} id={'name'} value={'홍길동'} />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label for='position' className='label-title'>직급</label>
            <InputBar inputId={'position'} id={'position'} value={'사원'} />
          </div>
          <div className='flex-div'>
            <label for='birth' className='label-title'>생년월일</label>
            <InputBar inputId={'birth'} id={'birth'} value={'1990-01-01'} />
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

      {/* </ Container> */}
    </div>
  )
}

export default UserNew
