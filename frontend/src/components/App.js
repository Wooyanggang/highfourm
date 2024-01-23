import React from 'react';
import SideBar from './Common/SideBar';
import Container from './Common/Container';
import { Layout, Menu, theme, Breadcrumb } from 'antd';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import OrderList from './View/Order/OrderList'
import ProductManagement from './View/Production/ProductManagement';
import Mrp from './View/Production/Mrp';
import UserNew from './View/User/UserNew';
import UserList from './View/User/UserList';
const { Header, Content, Footer, Sider } = Layout;

function App() {
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();
  return (
    <div className="App" style={{ position: 'relative' }}>
      <SideBar />
      <Container />
      <Content
        style={{
          padding: '152px 0 0 332px',
          background: colorBgContainer,
          borderRadius: borderRadiusLG,
          boxSizing: 'border-box',
        }}
      >
        <div>
          <Router>
            <Routes>
              <Route path='orders' element={<OrderList />} />
              <Route path='/users' element={<UserList />} />
              <Route path='/users/new' element={<UserNew />} />
              <Route path='/users/edit' element={<UserEdit />} />
              <Route path='/mrp' element={<Mrp />} />
              <Route path='/product-management' element={<ProductManagement />} />
            </Routes>
          </Router>
        </div>
      </Content>
    </div>
  );
}

export default App;
