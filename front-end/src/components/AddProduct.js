import React from 'react';
import './addProduct.css';

class AddProduct extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: '',
      price: '',
      unit: '',
      url: ''
    };
  }

  handleSubmit = event => {
    event.preventDefault();
    const { name, price, unit, url } = this.state;
    if (!(name && price && unit && url)) {
      console.log('Please fill all required fields');
    } else {
      console.log(this.state);
    }
  };

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  renderField = (field, text, placeholder) => (
    <label key={field}>
      <div className='field-title'><span className='field-required'>* </span>{text}</div>
      <div className='input-wrapper'>
        <input
          type='text'
          name={field}
          className='normal'
          placeholder={placeholder}
          onChange={this.handleChange}
          value={this.state[field]} />
      </div>
    </label>
  );

  fields = [
    {
      field: 'name',
      text: '名称',
      placeholder: '名称',
    },
    {
      field: 'price',
      text: '价格',
      placeholder: '价格',
    },
    {
      field: 'unit',
      text: '单位',
      placeholder: '单位',
    },
    {
      field: 'url',
      text: '图片',
      placeholder: 'URL',
    },
  ];

  render() {
    return (
      <div className='add-product'>
        <h2>添加商品</h2>
        <form onSubmit={this.handleSubmit}>
          {this.fields.map(({field, text, placeholder}) => this.renderField(field, text, placeholder))}
          <input type='submit' className='btn-submit' value='提交' />
        </form>
      </div>
    );
  }

}

export default AddProduct;
