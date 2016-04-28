<div class="dashlet">  
	<div class="title">${msg("header")}</div>  
	<div class="body scrollableList">  
		<div class="detail-list-item first-item last-item">  
			<div class="toolbar flat-button">
				<span class="first-child">
					<span>Due date</span>
                     <span>
                     	<button tabindex="0" type="button" aria-haspopup="true">==Select== ▾</button>
                     </span>
             	</span>
				<span class="first-child">
                     <span>Periority</span>
                     <span>
                     	<button tabindex="0" type="button" aria-haspopup="true">==Select== ▾</button>
                     </span>
              	</span>
				<span class="first-child">
                     <span>Due date</span>
                     <span>
                     	<button tabindex="0" type="button" aria-haspopup="true">==Select== ▾</button>
                     </span>
             	</span>                                    
				<div class="clear"></div>
			</div>
			<div class="completed-task-body">
				${msg("header")}<br />
				<#list tasks as task>
					${task.id} ${task.url}
				</#list>		
			</div>
		</div>  
	</div>  
 </div> 