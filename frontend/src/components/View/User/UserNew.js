import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { BtnBlue, BtnWhite, InputBar } from '../../Common/Module';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';

const UserNew = () => {

  const [htmlContent, setHtmlContent] = useState('');

  useEffect(() => {
    fetch('http://localhost:8080/users/new')
      .then(response => response.text())
      .then(data => setHtmlContent(data));
  }, []);
  

  // const navigate = useNavigate();
  // const [newUser, setNewUser] = useState(
  //   {
  //     empNo: '',
  //     userName: '',
  //     position: '',
  //     birth: '',
  //     email: '',
  //   });

  // const onChange = (e) => {
  //   setNewUser({
  //     ...newUser,
  //     [e.target.name]: e.target.value
  //   });
  // }

  // const onClickSubmit = (e) => {
  //   console.log(newUser)

  //   axios({
  //     method: 'POST',
  //     url: '/users/new',
  //     data: JSON.stringify(newUser),
  //     headers: { 'Content-Type': 'application/json' },
  //   })
  //     .then((res) => {
  //       console.log(res);
  //       navigate('/users');
  //     })
  //     .catch((error) => console.log(error))
  // }

  // const goBackNavigate = () => {
  //   navigate(-1)
  // }

  // const email = document.getElementById('email');
  // const emailCheck = document.getElementById('emailCheck');

  return (
    <div>
      <div dangerouslySetInnerHTML={{ __html: htmlContent }}></div>
      {/* <PageTitle value={'사용자 등록'} /> 
      <form id='userForm' method='post' action='/users/new'>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='empNo' className='label-title'>사번</label>
            <InputBar id={'empNo'} name={'empNo'} placeholderMsg={'사번'} onChange={onChange} required />
          </div>
          <div className='flex-div'>
            <label htmlFor='userName' className='label-title'>이름</label>
            <InputBar id={'userName'} name={'userName'} placeholderMsg={'이름'} onChange={onChange} />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='position' className='label-title'>직급</label>
            <InputBar id={'position'} name={'position'} placeholderMsg={'직급'} onChange={onChange} />
          </div>
          <div className='flex-div'>
            <label htmlFor='birth' className='label-title'>생년월일</label>
            <InputBar type={'date'} id={'birth'} name={'birth'} placeholderMsg={'생년월일'} onChange={onChange} />
          </div>
        </div>
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label htmlFor='email' className='label-title'>이메일 주소</label>
          <InputBar id={'email'} name={'email'} placeholderMsg={'이메일 주소'} onChange={onChange} />
          <span className='email'>@gmail.com</span>
        </div>
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label htmlFor='emailCheck' className='label-title'>이메일 주소 확인</label>
          <InputBar id={'emailCheck'} name={'emailCheck'} placeholderMsg={'이메일 주소 확인'} />
          <span className='email'>@gmail.com</span>
          {email != null && emailCheck != null ?
            (email.value === emailCheck.value ?
              <p>이메일이 일치합니다.</p> :
              <p style={{ color: '#f00' }}>이메일이 일치하지 않습니다.</p>) : ""}
        </div>
        <div className='flex-btn'>
          <BtnBlue type='submit' value={'등록하기'} onClick={onClickSubmit} />
          <BtnWhite value={'취소'} onClick={goBackNavigate} />
        </div>
      </form> */}
    </div>
  )
}

export default UserNew
