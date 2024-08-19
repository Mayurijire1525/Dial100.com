import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ children, requiredRole }) => {
  const user = JSON.parse(localStorage.getItem('user'));

  if (!user) {
    // If no user is found, redirect to login page
    return <Navigate to="/login" />;
  }

  if (user.role !== requiredRole) {
    // If user role doesn't match the requiredRole, redirect to the home page or unauthorized page
    return <Navigate to="/" />;
  }

  // If user is authenticated and role matches, render the child component
  return children;
};

export default ProtectedRoute;
