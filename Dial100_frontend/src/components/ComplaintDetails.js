import React from 'react';

function ComplaintDetails({ complaint }) {
  return (
    <div className="complaint-details">
      <h2>{complaint.title}</h2>
      <p>{complaint.description}</p>
      <p>Status: {complaint.status}</p>
    </div>
  );
}

export default ComplaintDetails;
