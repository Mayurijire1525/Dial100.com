import React from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Home from './pages/Home';
import Register from './pages/Register';
import Login from './pages/Login';
import ContactPage from './pages/Contact';
import PlaintiffDashboardPage from './pages/PlaintiffDashboard';
import ComplaintPage from './pages/Complaint';
import AuthorityDashboardPage from './pages/AuthorityDashboard';
import ViewComplaintsPage from './pages/ViewComplaints';
import PlaintiffComplaintDetailsPage from './pages/PlaintiffComplaintDetails';
import PlaintiffUpdateDetailsPage from './pages/PlaintiffUpdateDetails';
import ProtectedRoute from './components/ProtectedRoute';
import './App.css';
import AddEvidencePage from './pages/AddEvidence';
import AllNewComplaintsPage from './pages/AllNewComplaints';
import AuthorityComplaintDetails from './components/AuthorityComplaintDetails';
import AddInvestigateFormPage from './pages/AddInvestigateForm';
import MyInvestigationsPage from './pages/MyInvestigations';
import InvestigationDetailsPage from './pages/InvestigationDetails';
import ViewEvidencesPage from './pages/ViewEvidences';
import AddUpdatesFormPage from './pages/AddUpdatesForm';
import ProfileComponentPage from './pages/ProfileComponent';

// Component to conditionally render the Navbar
function ConditionalNavbar() {
  const location = useLocation();
  const hideNavbarPatterns = [
    '/plaintiff-dashboard',
    '/register-complaint',
    '/authority-dashboard',
    '/view-complaints',
    '/profile',
    /^\/plaintiff-complaint-details\/.+/,  // Pattern to match dynamic IDs
    /^\/plaintiff-update-details\/.+/,
    /^\/add-evidence\/.+/,
    '/all-new-complaints',
    /^\/authority-complaint-details\/.+/,
    /^\/add-investigate\/.+/,
    '/my-investigations',
    /^\/investigationdetails\/.+/,
    /^\/view-evidences\/.+/,
    /^\/addupdatesform\/.+/
  ];

  const shouldHideNavbar = hideNavbarPatterns.some(pattern =>
    typeof pattern === 'string'
      ? location.pathname === pattern
      : pattern.test(location.pathname)
  );

  return !shouldHideNavbar && <Navbar />;
}

function App() {
  return (
    <Router>
      <div className="app">
        <ConditionalNavbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/contact" element={<ContactPage />} />
          <Route path="/profile" element={<ProfileComponentPage />} />


          {/* Protected routes */}
          <Route
            path="/plaintiff-dashboard"
            element={
              <ProtectedRoute requiredRole="PLAINTIFF">
                <PlaintiffDashboardPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/register-complaint"
            element={
              <ProtectedRoute requiredRole="PLAINTIFF">
                <ComplaintPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/view-complaints"
            element={
              <ProtectedRoute requiredRole="PLAINTIFF">
                <ViewComplaintsPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/plaintiff-complaint-details/:complaintId"
            element={
              <ProtectedRoute requiredRole="PLAINTIFF">
                <PlaintiffComplaintDetailsPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/plaintiff-update-details/:complaintId"
            element={
              <ProtectedRoute requiredRole="PLAINTIFF">
                <PlaintiffUpdateDetailsPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/add-evidence/:complaintId"
            element={
              <ProtectedRoute requiredRole="PLAINTIFF">
                <AddEvidencePage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/authority-dashboard"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <AuthorityDashboardPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/all-new-complaints"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <AllNewComplaintsPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/authority-complaint-details/:complaintId"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <AuthorityComplaintDetails />
              </ProtectedRoute>
            }
          />
          <Route
            path="/add-investigate/:complaintId"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <AddInvestigateFormPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/my-investigations"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <MyInvestigationsPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/investigationdetails/:investigationId"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <InvestigationDetailsPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/view-evidences/:complaintId"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <ViewEvidencesPage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/addupdatesform/:investigationId"
            element={
              <ProtectedRoute requiredRole="AUTHORITY">
                <AddUpdatesFormPage />
              </ProtectedRoute>
            }
          />
          </Routes>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
