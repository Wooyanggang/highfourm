
import React, { useState } from 'react';

const UserNew = () => {
  const [newUser, setNewUser] = useState({
    empNo: '',
    userName: '',
    position: '',
    birth: '',
    email: '',
    emailCheck: '',
  });

  const [validationErrors, setValidationErrors] = useState({
    empNo: '',
    userName: '',
    position: '',
    birth: '',
    email: '',
    emailCheck: '',
  });

  const validateEmail = (email) => {
    // 간단한 이메일 형식 검증 예제
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const [isSubmitDisabled, setIsSubmitDisabled] = useState(true);

  const onChange = (e) => {
    setNewUser({
      ...newUser,
      [e.target.name]: e.target.value,
    });
  };

  const updateSubmitButton = () => {
    // 각 필드의 빈 값 및 이메일 형식 확인
    const errors = {
      empNo: newUser.empNo ? '' : '사번을 입력하세요.',
      userName: newUser.userName ? '' : '이름을 입력하세요.',
      position: newUser.position ? '' : '직급을 입력하세요.',
      birth: newUser.birth ? '' : '생년월일을 입력하세요.',
      email: validateEmail(newUser.email) ? '' : '올바른 이메일 주소를 입력하세요.',
      emailCheck:
        newUser.email === newUser.emailCheck ? '' : '이메일 주소가 일치하지 않습니다.',
    };

    setValidationErrors(errors);

    // 에러가 하나라도 있으면 제출하지 않음
    const isDisabled = Object.values(errors).some((error) => error !== '');
    setIsSubmitDisabled(isDisabled);
  };

  const onClickSubmit = (e) => {
    e.preventDefault();

    // 서버로 데이터 전송하는 코드 추가
    // axios.post('/users/new', newUser).then((response) => { ... }).catch((error) => { ... });
  };

  return (
    <div>
      <form>
        {/* ... 나머지 폼 입력 요소들 ... */}
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label htmlFor='email' className='label-title'>
            이메일 주소
          </label>
          <input
            type='text'
            id='email'
            name='email'
            placeholder='이메일 주소'
            onChange={onChange}
          />
          <span className='email'>@gmail.com</span>
          <p className='field-error'>{validationErrors.email}</p>
        </div>
        <div className='flex-div' style={{ marginBottom: '30px' }}>
          <label htmlFor='emailCheck' className='label-title'>
            이메일 주소 확인
          </label>
          <input
            type='text'
            id='emailCheck'
            name='emailCheck'
            placeholder='이메일 주소 확인'
            onChange={onChange}
          />
          <span className='email'>@gmail.com</span>
          <p className='field-error'>{validationErrors.emailCheck}</p>
        </div>
        <div className='flex-btn'>
          <button type='submit' onClick={onClickSubmit} disabled={isSubmitDisabled}>
            등록하기
          </button>
        </div>
      </form>
    </div>
  );
};

export default UserNew;
