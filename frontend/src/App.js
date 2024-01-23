import React from 'react';
import login from './component/login';
import table from './component/common/table';
import sidebar from './component/common/sidebar';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, SearchSelectBox, StepBar } from './component/common/module';
import container from './component/common/container';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {

  const arr = []

  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path='/' element={<login />} />
        </Routes>
      </Router>
      <h1>편의를 위해 만들어둠.  서버 정상 작동중</h1>
      <div>

      <BtnBlack value={'검정'} link={'/black'} />
      <BtnBlue value={'파랑'} link={'/blue'} />
      <BtnWhite value={'흰색'} link={'/white'} />
      <BtnFilter valueArr={['완료', '전체']} linkArr={['/complete', '/all']} />
      {/* <BtnFilter value={'진행'} link={'/progress'} /> */}
      <InputBar placeholderMsg={'값을 입력해주세요...'}/>
      <SearchInput />
      <SearchSelectBox selectValue={['가', '나', '다', '라', '마']} /> 
      <StepBar stateNum={1} />
      <StepBar stateNum={2} />
      <StepBar stateNum={3} />
      <StepBar stateNum={4} />

      </div>
    </div>
  );
}

export default App;
