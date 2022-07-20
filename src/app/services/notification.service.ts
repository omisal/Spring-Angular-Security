import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

@Injectable({
	providedIn: 'root'
})
export class NotificationService {
	constructor(private toastr: ToastrService) { }

	showSuccess(message: string, title: string) {
		message = title + "<br><span style='color:black'>" + message + "</span>"
		var Toast = Swal.mixin({
			toast: true,
			timerProgressBar: true,
			position: 'top-end',
			showConfirmButton: false,
			timer: 5000,
			color: '#3fb331'
		});
		Toast.fire({
			icon: 'success',
			title: message
		})
	}

	showError(message: string, title: string) {
		message = title + "<br><span style='color:black'>" + message + "</span>"
		var Toast = Swal.mixin({
			toast: true,
			timerProgressBar: true,
			position: 'top-end',
			showConfirmButton: false,
			timer: 5000,
			color: '#b33131'
		});
		Toast.fire({
			icon: 'error',
			title: message
		})
	}

	showSweetAllert2(): any {
		Swal.fire({
			title: 'Are you sure?',
			text: "You won't be able to revert this!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: 'Yes, delete it!',
			cancelButtonText: 'No, cancel!',
			reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) { return true; }
			else if (result.dismiss === Swal.DismissReason.cancel) { return false; }
			return false;
		})
	}
}
