// theme.js
import { createTheme } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        primary: {
            main: '#6366f1', // sua cor personalizada
            
        },
        secondary: {
            main: '#ff4081', // você pode ajustar ou remover
        },
        success:{
            main: '#3d5afe',
        }
    },
});

export default theme;
