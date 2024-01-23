import React from 'react';
import SideBar from './Common/SideBar';
import Container from './Common/Container';
import { Layout, Menu, theme, Breadcrumb } from 'antd';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import OrderList from './View/Order/OrderList'
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
          </Routes>
        </Router>
      </div>
      </Content>
    </div>
  );
  }

export default App;
