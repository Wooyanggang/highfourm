import { GoogleOAuthProvider } from '@react-oauth/google';
import { Layout, theme } from 'antd';
import React, { useEffect, useState } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import '../App.css';
import Container from './Common/Container';
import SideBar from './Common/SideBar';
import Login from './View/Login/Login';
import Notice from './View/Login/Notice';
import OrderList from './View/Order/OrderList';
import OrderNew from './View/Order/OrderNew';
import Bom from './View/Production/Bom';
import Mrp from './View/Production/Mrp';
import ProductManagement from './View/Production/ProductManagement';
import WorkPerfomance from './View/Production/WorkPerfomance';
import UserEdit from './View/User/UserEdit';
import UserList from './View/User/UserList';
import UserNew from './View/User/UserNew';
import axios from 'axios';
const { Header, Content, Footer, Sider } = Layout;

function App() {
  const [test, setTest] = useState('');

  useEffect(() => {
    axios.get("/api/hi")
      .then(res => res.json())
      .then(data => setTest(data))
      .catch(error => console.log(error))
  }, [])

  const clientId = '362376129818-apm2mmh54l58gskanrm2hufa2emfdov2.apps.googleusercontent.com';
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  if (window.location.pathname === '/login') {
    return (
      <GoogleOAuthProvider clientId={clientId}>
        <Login />
      </GoogleOAuthProvider>
    )
    return (<div><Notice /></div>)
  } else {
    return (
        <div>{test}</div>
      // <div className="App" style={{ position: 'relative' }}>
      //   <SideBar />
      //   <Container />
      //   <Content
      //     style={{
      //       padding: '152px 32px 32px 332px',
      //       background: colorBgContainer,
      //       borderRadius: borderRadiusLG,
      //       boxSizing: 'border-box',
      //     }}
      //   >
      //     <div>
      //       <Router>
      //         <Routes>
      //           <Route path='/orders' element={<OrderList />} />
      //           <Route path='/orders/new' element={<OrderNew />} />
      //           <Route path='/users' element={<UserList />} />
      //           <Route path='/users/new' element={<UserNew />} />
      //           <Route path='/users/edit' element={<UserEdit />} />
      //           <Route path='/mrp' element={<Mrp />} />
      //           <Route path='/product-management' element={<ProductManagement />} />
      //           <Route path='/bom' element={<Bom />} />
      //           <Route path='/work-perfomance' element={<WorkPerfomance />} />
      //         </Routes>
      //       </Router>
      //     </div>
      //   </Content>
      // </div>
    );
  }
}

export default App;
