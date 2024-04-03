//Author: Mateusz Dyla
import React from 'react'
import {styled } from '@mui/material/styles';
import { Button } from '@mui/material';


//Default button you can see in the app (navy background, yellow text)
export const DarkButton = styled(Button)(({theme}) => ({
    color: "#F0C24C",
    padding: "15px 70px",
    backgroundColor: "#091D28",
    borderRadius: 10,
    '&:hover': {
        color: "#F0C24C",
        backgroundColor: "#091D28",

    }
}));


