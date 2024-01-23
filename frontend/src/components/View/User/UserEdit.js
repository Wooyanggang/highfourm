import React from 'react'
import Container from '../../Common/Container'
import { BtnBlue, BtnWhite, InputBar } from '../../Common/Module'

const UserNew = () => {
  return (
    <div>
      <Container title={'사용자 수정'}>
        <form method='post' action='/users'>
          <div>
            <label for='empNo'>사번</label>
            <InputBar inputId={'empNo'} name='empNo' value={'사번'} />
          </div>
          <div>
            <label for='name'>이름</label>
            <InputBar inputId={'name'} name='name' value={'이름'} />
          </div>
          <div>
            <label for='position'>직급</label>
            <InputBar inputId={'position'} name='position' value={'직급'} />
          </div>
          <div>
            <label for='birth'>생년월일</label>
            <InputBar inputId={'birth'} name='birth' value={'생년월일'} />
          </div>
          <div>
            <label for='email'>이메일 주소</label>
            <InputBar inputId={'email'} name='email' value={'이메일 주소'} />
            <span>@gmail.com</span>
          </div>
          <div>
            <label for='emailCheck'>이메일 주소 확인</label>
            <InputBar inputId={'emailCheck'} name='emailCheck' value={'이메일 주소 확인'} />
            <span>@gmail.com</span>
          </div>
          <BtnBlue type='submit' value={'수정하기'} />
          <BtnWhite value={'취소'} link={history.back()} />
        </form>

      </ Container>
    </div>
  )
}

export default UserNew
