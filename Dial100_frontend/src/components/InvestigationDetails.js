import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/InvestigationDetails.css';

function InvestigationDetails() {
  const { investigationId } = useParams(); // Get the InvestigationId from the URL
  const [complaintDetails, setComplaintDetails] = useState(null);
  const [updates, setUpdates] = useState([]);
  const [investigationDetails, setInvestigationDetails] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const id = parseInt(investigationId, 10);

    if (Number.isInteger(id)) {
      // Fetch Complaint ID
      fetch(`http://localhost:8080/api/investigations/getCompId/${id}`)
        .then(response => response.json())
        .then(data => {
          const complaintID = data.complaintID;
          
          // Fetch Investigation Details
          return fetch(`http://localhost:8080/api/investigations/${id}`)
            .then(response => response.json())
            .then(investigationData => {
              setInvestigationDetails(investigationData);
              setComplaintDetails(data);

              if (complaintID) {
                // Fetch Complaint Updates using Complaint ID
                return fetch(`http://localhost:8080/api/updates/complaint/${complaintID}`);
              } else {
                // If no complaintID, set empty updates
                setUpdates([]);
                return Promise.resolve([]);
              }
            });
        })
        .then(response => response.json())
        .then(data => setUpdates(data))
        .catch(error => console.error("Error fetching data:", error));
    } else {
      console.error("Invalid InvestigationId");
      // Optionally, redirect or show an error message
    }
  }, [investigationId]);

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  // Function to navigate to the view evidences page
  const handleViewEvidences = () => {
    if (complaintDetails && complaintDetails.complaintID) {
      navigate(`/view-evidences/${complaintDetails.complaintID}`);
    }
  };

  return (
    <div className="dashboard">
      <div className="side-nav">
        <div className="logo">Dial100</div>
        <ul>
          <li><a href="/all-new-complaints">All New Complaints</a></li>
          <li><a href="/my-investigations">My Investigations</a></li>
        </ul>
      </div>
      
      <div className="content-area">
        <div className="top-nav">
          <input type="text" className="search-bar" placeholder="Search here..." />
          <div className="profile-options">
            <a href="/profile">Your Profile</a>
            <a href="/" onClick={handleLogout}>Logout</a>
          </div>
        </div>

        <div className="main-content">
          <h1>Investigation Details</h1>
          {investigationDetails ? (
            <div className="investigation-details">
              <p><strong>Complaint ID:</strong> {complaintDetails.complaintID}</p>
              <p><strong>Start Date:</strong> {investigationDetails.startDate}</p>
              <p><strong>End Date:</strong> {investigationDetails.endDate}</p>
              <p><strong>Report:</strong> {investigationDetails.report || 'No report available'}</p>

              {/* Button to view evidences */}
              <button className="view-evidences-btn" onClick={handleViewEvidences}>
                View Evidences
              </button>

              {updates.length > 0 ? (
                updates.map((update, index) => (
                  <div key={index} className="update-section">
                    <h3>Update ID {index + 1} :</h3>
                    <p><strong>Status:</strong> {update.status}</p>
                    <p><strong>Remarks:</strong> {update.remarks}</p>
                    <p><strong>Update Date:</strong> {update.updateDate}</p>
                  </div>
                ))
              ) : (
                <p>No updates available.</p>
              )}
            </div>
          ) : (
            <p>Loading investigation details...</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default InvestigationDetails;
