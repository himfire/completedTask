<@markup id="css" >
   <#-- CSS Dependencies -->
   <@link rel="stylesheet" type="text/css" href="${url.context}/res/components/dashlets/my-tasks.css" group="dashlets"  />
</@>

<@markup id="js">
   <#-- JavaScript Dependencies -->
   <@script type="text/javascript" src="${url.context}/res/components/workflow/workflow-actions.js" group="dashlets"/>
   <@script type="text/javascript" src="${url.context}/res/components/dashlets/my-tasks.js" group="dashlets"/>
</@>

<@markup id="widgets">
   <@createWidgets group="dashlets"/>
</@>

<@markup id="html">
   <@uniqueIdDiv>
      <#assign id = args.htmlid?html>
        <div style="width: 30%;float: left">
            <div>
                <form action="">
                    <ul>
                        <li>Due
                            <br />
                            <select>
                                <option value="today">Today</option>
                                <option value="tomorrow">Tomorrow</option>
                                <option value="next_7_days">Next 7 days</option>
                                <option value="overdue">Overdue</option>
                                <option value="no_date">No date</option>
                            </select>
                        </li>
                        <li>Periority
                            <br />
                            <select>
                                <option value="high">High</option>
                                <option value="medium">Medium</option>
                                <option value="low">Low</option>
                            </select>
                        </li>
                    </ul>
                    <input type="button" value="submit" />
                </form>
            </div>
        </div>
        <table style="width: 70%">
            <tr style="border:2px solid black">        
                <td>1</td>
                <td>hello</td>
            </tr>
            <tr>
                <td>2</td>
                <td>hello</td>
            </tr>
        </table>
   </@>
</@>