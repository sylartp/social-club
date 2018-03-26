import React from 'react';
import ReactDOM from 'react-dom';
import routes from './routes/routes';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';




ReactDOM.render(<MuiThemeProvider>{routes}</MuiThemeProvider>, document.getElementById('app'))