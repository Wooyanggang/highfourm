import React, { useEffect, useState } from 'react';
import { SearchInput, SearchSelectBox } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import PageTitle from '../../Common/PageTitle';
import axios from 'axios';
import Item from 'antd/es/list/Item';

const Mrp = () => {
  const [searchType, setSearchType] = useState('생산계획 코드');
  const [selectedProductionPlanId, setSelectedProductionPlanId] = useState();
  const [dataSourceOne, setDataSourceOne] = useState([]);
  const [dataSourceTwo, setDataSourceTwo] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await axios.get('/mrp')
        console.log(res.data);

        const mrpProductionData = await res.data.map((rowData) => (
          console.log("rowData : ", rowData),
          {
            key: rowData.productionPlanId,
            due_date: rowData.dueDate,
            production_plan_id: rowData.productionPlanId,
            product_id: rowData.productId,
            product_name: rowData.productName,
            production_plan_amount: rowData.productionPlanAmount,
          }
        ))
        setDataSourceOne(mrpProductionData);
        console.log("mrp : ", mrpProductionData);
      } catch (e) {
        console.error(e.message)
      }
    }
    fetchData();
  }, []);

  useEffect(() => {
    async function fetchData() {
      try {
        if (selectedProductionPlanId) {
          const res = await axios.get(`/mrp/${selectedProductionPlanId}`);
          const mrpMaterialData = res.data.map((rowData) => ({
            key: rowData.materialId,
            material_name: rowData.materialName,
            material_id: rowData.materialId,
            input_amount: rowData.inputAmount,
            total_material_amount: rowData.totalMaterialAmount,
            total_stock: rowData.totalStock,
            safety_stock: rowData.safetyStock,
            inbound_amount: rowData.inboundAmount,
          }));
          setDataSourceTwo(mrpMaterialData);
        } else {
          setDataSourceTwo([]);
        }
      } catch (e) {
        console.error(e.message);
      }
    }
    fetchData();
  }, [selectedProductionPlanId]);


  const defaultColumnsOne = [
    {
      title: '납기일',
      dataIndex: 'due_date',
      width: '20%',
      // production_plan
    },
    {
      title: '생산계획 코드',
      dataIndex: 'production_plan_id',
      render: (text) => <a href={`/mrp/${selectedProductionPlanId}`} onClick={handleClick}>{text}</a>,
      // production_plan
    },
    {
      title: '품번',
      dataIndex: 'product_id',
      // production_plan
    },
    {
      title: '품명',
      dataIndex: 'product_name',
      // production_plan(product_id) - product
    },
    {
      title: '계획 수량',
      dataIndex: 'production_plan_amount',
      // production_plan
    },
  ];

  const defaultColumnsTwo = [
    {
      title: '자재명',
      dataIndex: 'material_name',
      width: '20%',
      // production_plan(product_id) - required_material(material_id) - material(material_name)
    },
    {
      title: '자재 코드',
      dataIndex: 'material_id',
      // production_plan(product_id) - required_material(material_id)
    },
    {
      title: '투입량',
      dataIndex: 'input_amount',
      // production_plan(product_id) - required_material(input_amount)
    },
    {
      title: '총 소요 수량',
      dataIndex: 'total_material_amount',
      // production_plan(production_plan_amount) * required_material(input_amount)
    },
    {
      title: '현 재고',
      dataIndex: 'total_stock',
      // production_plan(product_id) - required_material(material_id) - material_stock(total_stock)
    },
    {
      title: '안전 재고',
      dataIndex: 'safety_stock',
      // production_plan(product_id) - required_material(material_id) - material_stock(safety_stock)
    },
    {
      title: '입고 예정량',
      dataIndex: 'inbound_amount',
      // production_plan(product_id) - required_material(material_id) - material_history(order_amount)
    },
  ];

  const SelectChangeHandler = (value) => {
    setSearchType(value);
    console.log(value);
    console.log(searchType);
  };

  const onSearch = (value) => {
    // search 값 기능 구현 함수
    console.log('search : ', value);
    axios({
      method: 'GET',
      url: '/users/search',
      params: {
        searchType: searchType, search: value,
      }
    })
      .catch(e => console.log(e));

    console.log(searchType);
    console.log('search2 : ', value);
  }

  const handleClick = (event) => {
    event.preventDefault();
    console.log(event);
    setSelectedProductionPlanId(event.target.text);
  }

  return (
    <div>
      <PageTitle value={'자재 소요량 산출'} />
      <div style={{ display: 'flex', gap: '10px 24px', marginBottom: '24px', alignItems: 'center' }}>
        <h2 style={{ fontSize: '16px', margin: 0 }}>생산계획 조회 조건</h2>
        <SearchSelectBox selectValue={['생산계획 코드', '품번', '품명', '납기일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>생산계획 상세</h2>
            <hr className='box-title-line' />
          </div>
          <div style={{ height: '706px', overflowY: 'auto' }}>
            <BasicTable dataSource={dataSourceOne} defaultColumns={defaultColumnsOne} setDataSource={setDataSourceOne} pagination={false} />
          </div>
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px', flexWrap: 'wrap' }}>
            <h2 className='bordered-box-title'>자재소요 계획</h2>
            <hr className='box-title-line' />
          </div>
          <div style={{ height: '706px', overflowY: 'auto' }}>
            <BasicTable dataSource={dataSourceTwo} defaultColumns={defaultColumnsTwo} setDataSource={setDataSourceTwo} pagination={false} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default Mrp

